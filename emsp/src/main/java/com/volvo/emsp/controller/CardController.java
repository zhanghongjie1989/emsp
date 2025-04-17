package com.volvo.emsp.controller;

import com.volvo.emsp.common.PageResult;
import com.volvo.emsp.domain.Card;
import com.volvo.emsp.domain.mapper.AccountSwitchMapper;
import com.volvo.emsp.domain.mapper.CardSwitchMapper;
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
    @Autowired
    private CardSwitchMapper cardSwitchMapper;

    @PostMapping("/card/create")
    public ResponseEntity<CardResponse> createCard(@RequestBody CardRequest request) {
        Card card = cardService.createCard(cardSwitchMapper.map2(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(cardSwitchMapper.map2(card));
    }

    @PostMapping("/{emaid}/assign")
    public ResponseEntity<CardResponse> assignCardToAccount(
            @PathVariable String emaid,
            @RequestParam String email) {
        Card card = cardService.assignCardToAccount(emaid, email);
        return ResponseEntity.ok(cardSwitchMapper.map2(card));
    }

    @PatchMapping("/{emaid}/status")
    public ResponseEntity<CardResponse> changeCardStatus(
            @PathVariable String emaid,
            @RequestParam Card.CardStatus newStatus) {
        return ResponseEntity.ok(cardSwitchMapper.map2(cardService.changeCardStatus(emaid, newStatus)));
    }

    @GetMapping
    public ResponseEntity<PageResult<CardResponse>> getCardsByLastUpdated(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime lastUpdated,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(PageResult.getPageResult(page, size, cardSwitchMapper.map2(cardService.getCardsByLastUpdated(lastUpdated, page, size))));
    }
}