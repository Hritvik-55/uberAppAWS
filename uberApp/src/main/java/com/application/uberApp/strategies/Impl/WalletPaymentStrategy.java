package com.application.uberApp.strategies.Impl;

import com.application.uberApp.entities.Driver;
import com.application.uberApp.entities.Payment;
import com.application.uberApp.entities.Rider;
import com.application.uberApp.entities.enums.PaymentStatus;
import com.application.uberApp.entities.enums.TransactionMethod;
import com.application.uberApp.repositories.PaymentRepository;
import com.application.uberApp.services.WalletService;
import com.application.uberApp.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {
    private final WalletService walletService;
//    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;
    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver=payment.getRide().getDriver();
        Rider rider=payment.getRide().getRider();
        walletService.deductMoneyFromWallet(rider.getUser(),payment.getAmount(),null,
                payment.getRide(), TransactionMethod.RIDE);
        double driversCut=payment.getAmount()*(1-PLATFORM_COMMISSION);
        walletService.addMoneyToWallet(driver.getUser(),driversCut,
                null,payment.getRide(),TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);

    }
}
