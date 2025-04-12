package com.volvo.emsp.controller;

import com.volvo.emsp.domain.Card;
import com.volvo.emsp.request.CardRequest;
import com.volvo.emsp.response.CardResponse;
import com.volvo.emsp.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/cards")
@Validated
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity<CardResponse> createCard(@RequestBody CardRequest request) {
        CardResponse response = cardService.createCard(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{emaid}/assign")
    public ResponseEntity<CardResponse> assignCardToAccount(
            @PathVariable String emaid,
            @RequestParam String email) {
        CardResponse response = cardService.assignCardToAccount(emaid, email);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{emaid}/status")
    public ResponseEntity<CardResponse> changeCardStatus(
            @PathVariable String emaid,
            @RequestParam Card.CardStatus newStatus) {
        CardResponse response = cardService.changeCardStatus(emaid, newStatus);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<CardResponse>> getCardsByLastUpdated(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime lastUpdated,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<CardResponse> response = cardService.getCardsByLastUpdated(lastUpdated, page, size);
        return ResponseEntity.ok(response);
    }
}