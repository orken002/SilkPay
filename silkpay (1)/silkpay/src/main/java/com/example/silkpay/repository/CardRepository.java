package com.example.silkpay.repository;

import com.example.silkpay.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {

    List<Card> findAllByActiveIsTrue();
    Optional<Card> findByCardNumber(String cardNumber);

    boolean existsByCardNumber(String cardNumber);

    Optional<Card> findByCardNumberAndActiveIsTrue(String cardNumber);

}
