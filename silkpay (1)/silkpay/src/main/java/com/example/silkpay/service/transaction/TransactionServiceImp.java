package com.example.silkpay.service.transaction;

import com.example.silkpay.model.entity.Card;
import com.example.silkpay.model.entity.Transaction;
import com.example.silkpay.model.enums.TransactionAction;
import com.example.silkpay.repository.TransactionRepository;
import com.example.silkpay.service.card.CardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImp implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CardService cardService;

    @Autowired
    public TransactionServiceImp(TransactionRepository transactionRepository,@Lazy CardService cardService) {
        this.transactionRepository = transactionRepository;
        this.cardService = cardService;
    }

    @Override
    @Transactional
    public void createTransaction(String receiverCardNumber, double amount, TransactionAction action) {
        Card receiver = cardService.getCardByCardNumber(receiverCardNumber);
        Transaction transaction = new Transaction(null, action, amount, null, receiver);
        transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void createTransaction(String receiverCardNumber, double amount, String comment, TransactionAction action) {
        Card receiver = cardService.getCardByCardNumber(receiverCardNumber);
        Transaction transaction = new Transaction(comment, action, amount, null, receiver);
        transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void createTransaction(String receiverCardNumber, String senderCardNumber, double amount, TransactionAction action) {
        Card sender = cardService.getCardByCardNumber(receiverCardNumber);
        Card receiver = cardService.getCardByCardNumber(receiverCardNumber);
        Transaction transaction = new Transaction(null, action, amount, sender, receiver);
        transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void createTransaction(String receiverCardNumber, String senderCardNumber, double amount, String comment, TransactionAction action) {
        Card card = cardService.getCardByCardNumber(receiverCardNumber);
        Transaction transaction = new Transaction(comment, action, amount, null, card);
        transactionRepository.save(transaction);
    }
}
