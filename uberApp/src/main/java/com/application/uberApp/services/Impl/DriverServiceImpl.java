package com.application.uberApp.services.Impl;

import com.application.uberApp.dto.DriverDTO;
import com.application.uberApp.dto.RideDTO;
import com.application.uberApp.dto.RiderDTO;
import com.application.uberApp.entities.Driver;
import com.application.uberApp.entities.Ride;
import com.application.uberApp.entities.RideRequest;
import com.application.uberApp.entities.User;
import com.application.uberApp.entities.enums.RideRequestStatus;
import com.application.uberApp.entities.enums.RideStatus;
import com.application.uberApp.exceptions.ResourceNotFoundException;
import com.application.uberApp.repositories.DriverRepository;
import com.application.uberApp.services.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper mapper;
    private final PaymentService paymentService;
    private final RatingService ratingService;

    @Override
    public RideDTO cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver=getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cannot start ride as he had not accepted");
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride cannot be cancelled, invalid status: "+ride.getRideStatus());
        }
        rideService.updateRideStatus(ride,RideStatus.CANCELLED);
        updateDriverAvailability(driver,true);
        return mapper.map(ride,RideDTO.class);
    }

    @Override
    public RideDTO startRide(Long rideId,String otp) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver=getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cannot start ride as he had not accepted");
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride status is not confirmed, hence cannot be started, staus: "+ride.getRideStatus());
        }
        if(!otp.equals(ride.getOtp())){
            throw new RuntimeException("OTP is not valid");
        }
        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);
        paymentService.createNewPayment(savedRide);
        ratingService.createNewRating(savedRide);


        return mapper.map(savedRide,RideDTO.class);
    }

    @Override
    public RideDTO endRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver=getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cannot start ride as he had not accepted");
        }
        if(!ride.getRideStatus().equals(RideStatus.ONGOING)){
            throw new RuntimeException("Ride status is not ONGOING, hence cannot be ended, staus: "+ride.getRideStatus());
        }
        ride.setEndedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ENDED);
        updateDriverAvailability(driver,true);
        paymentService.processPayment(ride);
        return mapper.map(savedRide,RideDTO.class);
    }

    @Override
    public RiderDTO rateRider(Long rideId, Integer rating) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver is not the owner of this ride");
        }
        if(!ride.getRideStatus().equals(RideStatus.ENDED)){
            throw new RuntimeException("Ride status is not ENDED, hence cannot start rating, staus: "+ride.getRideStatus());
        }
        return ratingService.rateRider(ride,rating);


    }


    @Override
    public RideDTO acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeException("Request cannot be accepted, status is "+rideRequest.getRideRequestStatus());
        }
        Driver currentDriver=getCurrentDriver();
        if(!currentDriver.getAvailable()){
            throw new RuntimeException("Driver cannot accept ride due to unavailability");
        }
        Driver savedDriver=updateDriverAvailability(currentDriver,false);

        Ride ride = rideService.createNewRide(rideRequest, savedDriver);
        return mapper.map(ride,RideDTO.class);
    }

    @Override
    public DriverDTO getMyProfile()  {
        Driver currentDriver = getCurrentDriver();
        return mapper.map(currentDriver,DriverDTO.class);
    }

    @Override
    public Page<RideDTO> getAllMyRides(PageRequest pageRequest) {
        Driver currentDriver = getCurrentDriver();
        return rideService.getAllRidesOfDriver(currentDriver,pageRequest).map(
                ride -> mapper.map(ride,RideDTO.class)
        );
    }

    @Override
    public Driver getCurrentDriver() {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return driverRepository.findByUser(user).orElseThrow(()->new ResourceNotFoundException("Driver not associated with user with id "+user.getId()));
    }

    @Override
    public Driver updateDriverAvailability(Driver driver, boolean available) {
        driver.setAvailable(available);
        return driverRepository.save(driver);



    }

    @Override
    public Driver createNewDriver(Driver driver) {
        return driverRepository.save(driver);
    }
}
