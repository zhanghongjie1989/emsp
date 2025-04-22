package com.volvo.emsp.controller.response;

import com.volvo.emsp.domain.Account;
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
public class AccountResponse {
    private String id;
    private String email;
    private Account.AccountStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
