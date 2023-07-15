package com.example.silkpay.service.card;

import com.example.silkpay.model.entity.Card;
import com.example.silkpay.model.enums.TransactionAction;
import com.example.silkpay.model.request.CardRequest;

import java.util.List;

public interface CardService {

    List<Card> getAllCards();

//    List<Card> findMyCards();
    Card getCardByCardNumber(String cardNumber);
    String createCard(CardRequest cardRequest);
    void sendMoney(String senderCardNumber,
                     String receiverCardNumber,
                     double amount,
                   String comment);
    void updateCard(String cardNumber, Double amount, TransactionAction action);
    void deleteCard(String cardNumber);

}
