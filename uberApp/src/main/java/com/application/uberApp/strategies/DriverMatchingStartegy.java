package com.application.uberApp.strategies;

import com.application.uberApp.entities.Driver;
import com.application.uberApp.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStartegy {
    List<Driver> findMatchingDrivers(RideRequest rideRequest);
}
