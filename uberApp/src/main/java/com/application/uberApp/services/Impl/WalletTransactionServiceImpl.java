package com.application.uberApp.services.Impl;

import com.application.uberApp.entities.WalletTransaction;
import com.application.uberApp.repositories.WalletTransactionRepository;
import com.application.uberApp.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {
    private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;
    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
//        WalletTransaction walletTransaction = modelMapper.map(walletTransactionDTO, WalletTransaction.class);
        walletTransactionRepository.save(walletTransaction);
    }
}