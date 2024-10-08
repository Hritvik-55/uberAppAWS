package com.application.uberApp.dto;

import com.application.uberApp.entities.enums.PaymentMethod;
import com.application.uberApp.entities.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideDTO {

    private Long id;

    private RiderDTO rider;
    private DriverDTO driver;
    private PointDTO pickupLocation;
    private PointDTO dropOffLocation;
    private RideStatus rideStatus;

    private LocalDateTime createdTime;
    private PaymentMethod paymentMethod;
    private Double fare;
    private String otp;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

}
