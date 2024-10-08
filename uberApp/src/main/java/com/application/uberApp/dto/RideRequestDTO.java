package com.application.uberApp.dto;

import com.application.uberApp.entities.enums.PaymentMethod;
import com.application.uberApp.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDTO {
    private Long id;
    private PointDTO pickUpLocation;
    private PointDTO dropOffLocation;
    private LocalDateTime requestedTime;
    private RiderDTO rider;
    private PaymentMethod paymentMethod;
    private RideRequestStatus rideRequestStatus;
    private Double fare;

}
