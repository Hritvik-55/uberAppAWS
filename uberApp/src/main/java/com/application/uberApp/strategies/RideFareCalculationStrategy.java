package com.application.uberApp.strategies;

import com.application.uberApp.entities.RideRequest;

public interface RideFareCalculationStrategy {
    double RIDE_FARE_MULTIPLIER=10;
    double SURGE_FACTOR=1.5;
    double calculateFare(RideRequest rideRequest);
}
