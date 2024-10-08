package com.application.uberApp.services.Impl;

import com.application.uberApp.entities.Driver;
import com.application.uberApp.entities.Ride;
import com.application.uberApp.entities.RideRequest;
import com.application.uberApp.entities.Rider;
import com.application.uberApp.entities.enums.RideRequestStatus;
import com.application.uberApp.entities.enums.RideStatus;
import com.application.uberApp.exceptions.ResourceNotFoundException;
import com.application.uberApp.repositories.RideRepository;
import com.application.uberApp.services.RideRequestService;
import com.application.uberApp.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {
    private final RideRepository rideRepository;
    private final RideRequestService rideRequestService;
    private final ModelMapper mapper;

    @Override
    public Ride getRideById(Long rideId) {
        return rideRepository.findById(rideId).orElseThrow(()-> new ResourceNotFoundException("Ride not found with id: "+rideId));
    }



    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);
        Ride ride = mapper.map(rideRequest, Ride.class);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setDriver(driver);
        ride.setOtp(generateRandomOTP());
        ride.setId(null);
        rideRequestService.update(rideRequest);
        return rideRepository.save(ride);
 }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setRideStatus(rideStatus);
        return rideRepository.save(ride);
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest) {
        return rideRepository.findByRider(rider,pageRequest);
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest) {
        return rideRepository.findByDriver(driver,pageRequest);

    }
    private String generateRandomOTP(){
        Random random=new Random();
        // Will give number from 0 to 10000
        int i = random.nextInt(10000);
        return String.format("%04d",i);
    }
}
