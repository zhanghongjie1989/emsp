package com.volvo.emsp.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;


public class Card {

    private Long id;
    private String emaid;
    private String contractId;
    private Account account;
    private CardStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and Setters
    public enum CardStatus {
        CREATED, ASSIGNED, ACTIVATED, DEACTIVATED
    }
}