package com.application.uberApp.services;

import com.application.uberApp.dto.DriverDTO;
import com.application.uberApp.dto.RideDTO;
import com.application.uberApp.dto.RiderDTO;
import com.application.uberApp.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DriverService {
    RideDTO cancelRide(Long rideId);
    RideDTO startRide(Long rideId,String otp);
    RideDTO endRide(Long rideId);
    RiderDTO rateRider(Long rideId,Integer rating);
    RideDTO acceptRide(Long rideRequestId);
    DriverDTO getMyProfile();
    Page<RideDTO> getAllMyRides(PageRequest pageRequest);

    Driver getCurrentDriver();
    Driver updateDriverAvailability(Driver driver,boolean available);
    Driver createNewDriver(Driver driver);

}
