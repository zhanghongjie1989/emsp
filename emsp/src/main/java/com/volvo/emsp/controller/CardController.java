package com.volvo.emsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity<CardResponse> createCard(@Valid @RequestBody CardRequest request) {
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
            @RequestParam CardStatus newStatus) {
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