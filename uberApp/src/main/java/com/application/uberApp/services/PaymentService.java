package com.application.uberApp.services;

import com.application.uberApp.entities.Payment;
import com.application.uberApp.entities.Ride;
import com.application.uberApp.entities.enums.PaymentStatus;

public interface PaymentService {
    void processPayment(Ride ride);
    Payment createNewPayment(Ride ride);
    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
