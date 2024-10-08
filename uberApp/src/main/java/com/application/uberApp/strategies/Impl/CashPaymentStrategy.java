package com.application.uberApp.strategies.Impl;

import com.application.uberApp.entities.Driver;
import com.application.uberApp.entities.Payment;
import com.application.uberApp.entities.enums.PaymentStatus;
import com.application.uberApp.entities.enums.TransactionMethod;
import com.application.uberApp.repositories.PaymentRepository;
import com.application.uberApp.services.WalletService;
import com.application.uberApp.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {
    private final WalletService walletService;
    private final PaymentRepository paymentRepository;
    @Override
    public void processPayment(Payment payment) {
        Driver driver=payment.getRide().getDriver();
        double platformCommission=payment.getAmount()*PLATFORM_COMMISSION;
        walletService.deductMoneyFromWallet(driver.getUser(),platformCommission,null,payment.getRide(), TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);


    }
}
