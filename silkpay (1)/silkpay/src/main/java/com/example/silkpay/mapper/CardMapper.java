package com.example.silkpay.mapper;


import com.example.silkpay.model.entity.Card;
import com.example.silkpay.model.entity.Transaction;
import com.example.silkpay.model.entity.User;
import com.example.silkpay.model.request.CardRequest;
import com.example.silkpay.model.response.CardResponse;
import com.example.silkpay.model.response.TransactionResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CardMapper {

    public static List<CardResponse> toDto(List<Card> cards){
        return cards.stream().map(CardMapper::toDto).collect(Collectors.toList());
    }

    public static CardResponse toDto(Card card){
        List<TransactionResponse> transactions = new ArrayList<>();
        List<TransactionResponse> receivedTransactions = TransactionMapper.toReceivedDto(card.getReceivedTransactions());
        List<TransactionResponse> sentTransactions = TransactionMapper.toSentDto(card.getSentTransactions());

        transactions.addAll(receivedTransactions);
        transactions.addAll(sentTransactions);

        return CardResponse.builder()
                .cardNumber(card.getCardNumber())
                .amount(card.getAmount())
                .holderFullName(getUserFullName(card.getHolder()))
                .transactions(transactions)
                .build();
    }
    public static Card fromDto(CardRequest cardRequest,User user, String cardNumber){
        return Card.builder()
                .cardNumber(cardNumber)
                .amount(cardRequest.getAmount())
                .holder(user)
                .active(true)
                .build();
    }

    public static String getUserFullName(User user){
        return String.format(
                "%s %s %s",
                user.getSurname(),
                user.getName(),
                user.getPatronymic());
    };

}
