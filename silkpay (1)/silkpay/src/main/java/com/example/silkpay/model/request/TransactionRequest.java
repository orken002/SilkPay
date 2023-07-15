package com.example.silkpay.model.request;


import lombok.Data;

@Data
public class TransactionRequest {

    private String senderCardNumber;
    private String receiverCardNumber;
    private Double amount;
    private String comment;

}
