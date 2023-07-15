package com.example.silkpay.model.entity;

import com.example.silkpay.model.aduit.AuditModel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "cards")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Card extends AuditModel {

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "amount")
    private double amount;

    private boolean active;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User holder;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "senderCard")
    private List<Transaction> sentTransactions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "receiverCard")
    private List<Transaction> receivedTransactions = new ArrayList<>();

}


