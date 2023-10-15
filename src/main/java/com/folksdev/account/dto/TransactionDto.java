package com.folksdev.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDto {
    private String id;
    private String type;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
}
