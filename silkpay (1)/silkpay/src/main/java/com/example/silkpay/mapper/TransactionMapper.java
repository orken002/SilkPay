package com.example.silkpay.mapper;

import com.example.silkpay.model.entity.Transaction;
import com.example.silkpay.model.enums.TransactionAction;
import com.example.silkpay.model.response.TransactionResponse;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionMapper {

    public static List<TransactionResponse> toSentDto(List<Transaction> transactions) {
        return transactions.stream().map(TransactionMapper::toSentDto).collect(Collectors.toList());
    }

    public static List<TransactionResponse> toReceivedDto(List<Transaction> transactions) {
        return transactions.stream().map(TransactionMapper::toReceivedDto).collect(Collectors.toList());
    }

    public static TransactionResponse toSentDto(Transaction transaction) {
        TransactionAction action = transaction.getAction().equals(TransactionAction.WITHDRAWAL)? TransactionAction.WITHDRAWAL : TransactionAction.REPLENISHMENT;
        transaction.setAction(action);
        return toDto(transaction);
    }

    public static TransactionResponse toReceivedDto(Transaction transaction) {
        TransactionAction action = transaction.getAction().equals(TransactionAction.REPLENISHMENT)? TransactionAction.REPLENISHMENT : TransactionAction.WITHDRAWAL;
        transaction.setAction(action);
        return toDto(transaction);
    }

    public static TransactionResponse toDto(Transaction transaction) {
        return TransactionResponse
                .builder()
                .transactionDate(transaction.getCreatedAt())
                .action(transaction.getAction().label)
                .amount(transaction.getAmount())
                .build();
    }

}
