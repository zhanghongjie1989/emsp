package com.volvo.emsp.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class Card {

    private Long id;
    private String emaid;
    private String contractId;
    private Account account;
    private CardStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and Setters
    @Getter
    @RequiredArgsConstructor
    public enum CardStatus {
        CREATED, ASSIGNED, ACTIVATED, DEACTIVATED
    }

}