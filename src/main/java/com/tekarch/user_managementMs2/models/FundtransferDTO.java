package com.tekarch.user_managementMs2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FundtransferDTO {

    private Long transferId;
    private Long senderAccountId;
    private Long receiverAccountId;
    private BigDecimal amount;

}
