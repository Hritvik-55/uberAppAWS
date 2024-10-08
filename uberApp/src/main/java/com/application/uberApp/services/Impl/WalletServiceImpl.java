package com.application.uberApp.services.Impl;

import com.application.uberApp.entities.Ride;
import com.application.uberApp.entities.User;
import com.application.uberApp.entities.Wallet;
import com.application.uberApp.entities.WalletTransaction;
import com.application.uberApp.entities.enums.TransactionMethod;
import com.application.uberApp.entities.enums.TransactionType;
import com.application.uberApp.exceptions.ResourceNotFoundException;
import com.application.uberApp.repositories.WalletRepository;
import com.application.uberApp.services.WalletService;
import com.application.uberApp.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final ModelMapper modelMapper;
    private final WalletTransactionService walletTransactionService;

    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
       Wallet wallet=findByUser(user);
       wallet.setBalance(wallet.getBalance()+amount);
       WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
               .transactionType(TransactionType.CREDIT)
               .transactionMethod(transactionMethod)
               .amount(amount)
               .build();

       walletTransactionService.createNewWalletTransaction(walletTransaction);

        return walletRepository.save(wallet);
    }

    @Override
    public void withdrawAllMyMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId).orElseThrow(()->new ResourceNotFoundException("Wallet not found with id: "+walletId));
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet=new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository.findByUser(user).orElseThrow(
                ()->new ResourceNotFoundException("Wallet not found for user: "+user.getId()));
    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet=findByUser(user);
        wallet.setBalance(wallet.getBalance()-amount);
        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.DEBIT)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();

//        walletTransactionService.createNewWalletTransaction(walletTransaction);
        wallet.getWalletTransaction().add(walletTransaction);

        return walletRepository.save(wallet);
    }
}
