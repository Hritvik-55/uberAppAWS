package com.application.uberApp.dto;

import java.util.List;

public class WalletDTO {
    private Long id;
    private UserDTO user;
    private Double balance;
    private List<WalletTransactionDTO> walletTransaction;
}
