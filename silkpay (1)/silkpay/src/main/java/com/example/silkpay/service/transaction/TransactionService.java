package com.example.silkpay.service.transaction;

import com.example.silkpay.model.enums.TransactionAction;

public interface TransactionService {

    void createTransaction(String receiverCardNumber, double amount, TransactionAction action);

    void createTransaction(String receiverCardNumber, double amount, String comment, TransactionAction action);

    void createTransaction(String receiverCardNumber, String senderCardNumber, double amount, TransactionAction action);

    void createTransaction(String receiverCardNumber, String senderCardNumber, double amount,String comment, TransactionAction action);
}
