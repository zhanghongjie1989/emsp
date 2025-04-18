package com.volvo.emsp.domain;


import com.volvo.emsp.common.RFIDUIDGenerator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class Card {

    private Long id;
    private String UID;
    private String emaid;
    private String accountId;
    private CardStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and Setters
    @Getter
    @RequiredArgsConstructor
    public enum CardStatus {
        CREATED, ASSIGNED, ACTIVATED, DEACTIVATED
    }

    public String getUID() {
        return RFIDUIDGenerator.generateUID();
    }

}