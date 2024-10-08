package com.application.uberApp.services;

import com.application.uberApp.dto.DriverDTO;
import com.application.uberApp.dto.RiderDTO;
import com.application.uberApp.entities.Ride;

public interface RatingService {
    DriverDTO rateDriver(Ride ride, Integer rating);
    RiderDTO rateRider(Ride ride, Integer rating);
    void createNewRating(Ride ride);

}
