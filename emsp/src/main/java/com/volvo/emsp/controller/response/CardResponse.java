package com.volvo.emsp.controller.response;

import com.volvo.emsp.domain.Account;
import com.volvo.emsp.domain.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/**
 * 用于封装账户信息的响应类。
 * 此类通常作为服务端返回给客户端的数据结构，包含了用户的基本信息及状态。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardResponse {
    private Long id;
    private String email;
    private Card.CardStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
