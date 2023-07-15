package com.example.silkpay.service.card;

//import com.example.silkpay.config.AuditorUtils;

import com.example.silkpay.mapper.CardMapper;
import com.example.silkpay.model.entity.Card;
import com.example.silkpay.model.entity.User;
import com.example.silkpay.model.enums.ErrorCode;
import com.example.silkpay.model.enums.TransactionAction;
import com.example.silkpay.model.exeptions.ServiceException;
import com.example.silkpay.model.request.CardRequest;
import com.example.silkpay.repository.CardRepository;
import com.example.silkpay.service.transaction.TransactionService;
import com.example.silkpay.service.user.UserService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CardServiceImp implements CardService {
    private final CardRepository cardRepository;
    private final UserService userService;
    private final TransactionService transactionService;

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAllByActiveIsTrue();
    }

//    @Override
//    public List<Card> findMyCards() {
//        String username = AuditorUtils.getCurrentUser().getUsername();
//        User user = userService.findUserByUsername(username);
//        return user.getCards();
//    }

    @Override
    public Card getCardByCardNumber(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new ServiceException(
                        HttpStatus.NOT_FOUND,
                        ErrorCode.NOT_FOUND,
                        String.format("card number: %s not found", cardNumber)
                ));
    }


    @Override
    @Transactional
    public String createCard(CardRequest cardRequest) {
        String cardNumber = generateCardNumber();
        User user = userService.findUserByUsername(cardRequest.getUsername());
        cardRepository.save(CardMapper.fromDto(cardRequest, user, cardNumber));
        return cardNumber;
    }

    @Override
    @Transactional
    public void sendMoney(String senderCardNumber, String receiverCardNumber,
                          double amount, String comment) {
        Card senderCard = getCardByCardNumber(senderCardNumber);
        Card receiverCard = getCardByCardNumber(receiverCardNumber);
        if (senderCard.getAmount() < amount) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_ARGUMENT, "Card sum is less then required for withdrawal");
        }

        senderCard.setAmount(senderCard.getAmount() - amount);
        receiverCard.setAmount(senderCard.getAmount() + amount);
        cardRepository.save(senderCard);
        cardRepository.save(receiverCard);
        transactionService.createTransaction(receiverCardNumber, senderCardNumber, amount, comment, TransactionAction.WITHDRAWAL);
    }

    @Override
    @Transactional
    public void updateCard(String cardNumber, Double amount, TransactionAction action) {
        Card card = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new ServiceException(
                        HttpStatus.NOT_FOUND,
                        ErrorCode.NOT_FOUND,
                        String.format("card number: %s not found", cardNumber)
                ));
        double cardAmount = card.getAmount();
        switch (action) {
            case WITHDRAWAL -> {
                if (cardAmount < amount)
                    throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_ARGUMENT, "Card sum is less then required for withdrawal");

                cardAmount -= amount;
            }
            case REPLENISHMENT -> cardAmount += amount;
        }
        card.setAmount(cardAmount);
        cardRepository.save(card);
        transactionService.createTransaction(cardNumber, amount, "Card system amount correction", action);
    }

    @Override
    @Transactional
    public void deleteCard(String cardNumber) {
        Card card = cardRepository.findByCardNumberAndActiveIsTrue(cardNumber)
                .orElseThrow(() -> new ServiceException(
                        HttpStatus.NOT_FOUND,
                        ErrorCode.NOT_FOUND,
                        String.format("card number: %s doesn't exit", cardNumber)));
        card.setActive(false);
        card.setDeletedAt(new Date());
        cardRepository.save(card);
    }

    private String generateCardNumber() {
        String cardNumber = RandomStringUtils.random(12, false, true);
        boolean isExist = cardRepository.existsByCardNumber(cardNumber);
        if (isExist) {
            return generateCardNumber();
        }

        return cardNumber;
    }

}
