package com.application.uberApp.strategies.Impl;

import com.application.uberApp.entities.Driver;
import com.application.uberApp.entities.RideRequest;
import com.application.uberApp.repositories.DriverRepository;
import com.application.uberApp.strategies.DriverMatchingStartegy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DriverMatchingNearestDriver implements DriverMatchingStartegy {
    private final DriverRepository driverRepository;
    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
        return driverRepository.findTenNearestDrivers(rideRequest.getPickUpLocation());
    }
}
