package com.application.uberApp.services;

import com.application.uberApp.dto.DriverDTO;
import com.application.uberApp.dto.RideDTO;
import com.application.uberApp.dto.RideRequestDTO;
import com.application.uberApp.dto.RiderDTO;
import com.application.uberApp.entities.Rider;
import com.application.uberApp.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RiderService {
    RideDTO cancelRide(Long rideId);
    DriverDTO rateDriver(Long rideId, Integer rating);
    RideRequestDTO requestRide(RideRequestDTO rideRequestDTO);
    RiderDTO getMyProfile();
    Page<RideDTO> getAllMyRides(PageRequest pageRequest);

    Rider createNewRider(User savedUser);

    Rider getCurrentRider();
}
