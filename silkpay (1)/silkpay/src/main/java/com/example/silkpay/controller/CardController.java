package com.example.silkpay.controller;

import com.example.silkpay.mapper.CardMapper;
import com.example.silkpay.model.entity.Card;
import com.example.silkpay.model.enums.TransactionAction;
import com.example.silkpay.model.request.CardRequest;
import com.example.silkpay.model.response.CardResponse;
import com.example.silkpay.service.card.CardService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    @GetMapping(path = "/")
    public ResponseEntity<List<CardResponse>> getAllCards() {
        log.info("Received request to get all cards");
        List<Card> cards = cardService.getAllCards();
        return ResponseEntity.ok(CardMapper.toDto(cards));
    }

    @GetMapping(path = "/{cardNumber}")
    public ResponseEntity<CardResponse> getCardByNumber(@PathVariable(name = "cardNumber") String cardNumber) {
        log.info("Received request to get information about a card with card number {}", cardNumber);
        Card card = cardService.getCardByCardNumber(cardNumber);
        return ResponseEntity.ok(CardMapper.toDto(card));
    }

    @PostMapping(path = "/")
    public ResponseEntity<String> createCard(@RequestBody @Valid CardRequest cardRequest) {
        log.info("Received request to create a card for user with username: {}", cardRequest.getUsername());
        return ResponseEntity.ok(cardService.createCard(cardRequest));
    }

    @PostMapping(path = "/")
    public ResponseEntity<Void> sendMoney(@RequestParam(name = "sender_card_number") String senderCardNumber,
                                          @RequestParam(name = "receiver_card_number") String receiverCardNumber,
                                          @RequestParam(name = "amount") double amount,
                                          @RequestParam(name = "comment", required = false) String comment) {
        log.info("Received send money from: {} to {} sum {}", senderCardNumber, receiverCardNumber, amount);
        cardService.sendMoney(senderCardNumber, receiverCardNumber, amount,comment);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/")
    public ResponseEntity<Void> updateCard(@RequestParam(name = "card_number") String cardNumber,
                                           @RequestParam(name = "amount") Double amount,
                                           @RequestParam(name = "action") TransactionAction action) {
        cardService.updateCard(cardNumber, amount, action);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{cardNumber}")
    public ResponseEntity<Void> deleteCard(@PathVariable(name = "cardNumber") String cardNumber) {
        cardService.deleteCard(cardNumber);
        return ResponseEntity.ok().build();
    }

}
