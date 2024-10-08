package com.application.uberApp.strategies;

import com.application.uberApp.entities.Payment;

public interface PaymentStrategy {
    static final Double PLATFORM_COMMISSION=0.3;
    void processPayment(Payment payment);
}
