package com.application.uberApp.services;

import com.application.uberApp.entities.Driver;
import com.application.uberApp.entities.Ride;
import com.application.uberApp.entities.RideRequest;
import com.application.uberApp.entities.Rider;
import com.application.uberApp.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {
    Ride getRideById(Long rideId);
    Ride createNewRide(RideRequest rideRequest, Driver driver);
    Ride updateRideStatus(Ride ride, RideStatus rideStatus);
    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);
    Page<Ride> getAllRidesOfDriver(Driver driver,PageRequest pageRequest);
}
