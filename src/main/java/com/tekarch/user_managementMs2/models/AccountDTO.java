package com.tekarch.user_managementMs2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDTO {

    private Long accountId;
    private String account_number;
    private String account_type;
    private BigDecimal balance = BigDecimal.ZERO;
    private String currency = "USD";
    private Long userId;
}
