package com.application.uberApp.strategies.Impl;

import com.application.uberApp.entities.RideRequest;
import com.application.uberApp.services.DistanceService;
import com.application.uberApp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {
    private final DistanceService distanceService;
    @Override
    public double calculateFare(RideRequest rideRequest) {
        Double distance=distanceService.calculateDistance(rideRequest.getPickUpLocation(),rideRequest.getDropOffLocation());

        return distance*RIDE_FARE_MULTIPLIER;
    }
}
