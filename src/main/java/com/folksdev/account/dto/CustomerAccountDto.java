package com.folksdev.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

// customer bilgisinin olmadığı dto cesidi.
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerAccountDto {
    private String id;
    private BigDecimal balance;
    private List<TransactionDto> transactions;
    private LocalDateTime creationDate;



}
