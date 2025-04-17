package com.volvo.emsp.domain;

import com.volvo.emsp.common.EMAIDGenerator;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private String id;
    private String email;
    private String contractId;
    private AccountStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // Getters and Setters
    @Getter
    @RequiredArgsConstructor
    public enum AccountStatus {
        CREATED, ACTIVATED, DEACTIVATED
    }

    public String generateEmaid() {
       return EMAIDGenerator.generate("CN","VOL");
    }
}