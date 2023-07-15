package com.example.silkpay.model.entity;

import com.example.silkpay.model.aduit.AuditModel;
import com.example.silkpay.model.enums.TransactionAction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Transaction extends AuditModel {

    @Column(name = "comment")
    private String comment;

    @Column(name = "action")
    @Enumerated(EnumType.STRING)
    private TransactionAction action;

    @Column(name = "amount")
    private double amount;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_card_id")
    private Card senderCard;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "receiver_card_id")
    private Card receiverCard;
}



