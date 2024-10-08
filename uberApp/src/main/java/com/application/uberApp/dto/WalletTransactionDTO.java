package com.application.uberApp.dto;

import com.application.uberApp.entities.enums.TransactionMethod;
import com.application.uberApp.entities.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletTransactionDTO {
    private Long id;

    private Double amount;
    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    private RideDTO ride;
    private String transactionId;
    private WalletDTO wallet;
    private LocalDateTime timeStamp;
}
