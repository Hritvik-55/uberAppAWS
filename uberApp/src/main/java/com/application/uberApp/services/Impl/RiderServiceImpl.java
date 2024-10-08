package com.application.uberApp.services.Impl;

import com.application.uberApp.dto.DriverDTO;
import com.application.uberApp.dto.RideDTO;
import com.application.uberApp.dto.RideRequestDTO;
import com.application.uberApp.dto.RiderDTO;
import com.application.uberApp.entities.*;
import com.application.uberApp.entities.enums.RideRequestStatus;
import com.application.uberApp.entities.enums.RideStatus;
import com.application.uberApp.exceptions.ResourceNotFoundException;
import com.application.uberApp.repositories.RideRequestRepository;
import com.application.uberApp.repositories.RiderRepository;
import com.application.uberApp.services.DriverService;
import com.application.uberApp.services.RatingService;
import com.application.uberApp.services.RideService;
import com.application.uberApp.services.RiderService;
import com.application.uberApp.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {
    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideService rideService;
    private final DriverService driverService;
    private final RatingService ratingService;

    @Override
    public RideDTO cancelRide(Long rideId)
    {
        Rider currentRider = getCurrentRider();
        Ride ride = rideService.getRideById(rideId);
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException(("Rider does not own ride with id:"+rideId ));
        }
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        driverService.updateDriverAvailability(ride.getDriver(),true);



        return modelMapper.map(savedRide,RideDTO.class);
    }

    @Override
    public DriverDTO rateDriver(Long rideId, Integer rating) {
        Ride ride = rideService.getRideById(rideId);
        Rider rider = getCurrentRider();
        if(!rider.equals(ride.getRider())){
            throw new RuntimeException("Rider is not the owner of this ride");
        }
        if(!ride.getRideStatus().equals(RideStatus.ENDED)){
            throw new RuntimeException("Ride status is not ENDED, hence cannot start rating, staus: "+ride.getRideStatus());
        }
        return ratingService.rateDriver(ride,rating);

    }

    @Override
    @Transactional
    public RideRequestDTO requestRide(RideRequestDTO rideRequestDTO) {
        Rider rider=getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDTO, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);
        log.info(String.valueOf(rider));
        double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);
        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        List<Driver> matchingDrivers = rideStrategyManager.driverMatchingStartegy(rider.getRating()).findMatchingDrivers(rideRequest);
        //TODO Send the notification to all the drivers
        return modelMapper.map(savedRideRequest,RideRequestDTO.class);
    }

    @Override
    public RiderDTO getMyProfile() {
        Rider currentRider = getCurrentRider();

        return modelMapper.map(currentRider,RiderDTO.class);
    }

    @Override
    public Page<RideDTO> getAllMyRides(PageRequest pageRequest) {
        Rider currentRider = getCurrentRider();
        return rideService.getAllRidesOfRider(currentRider,pageRequest).map(
                ride -> modelMapper.map(ride,RideDTO.class)
        );

    }

    @Override
    public Rider createNewRider(User savedUser) {
        Rider rider= Rider.builder()
                .user(savedUser)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // TODO Implement Spring security
        return riderRepository.findByUser(user).orElseThrow(()->new ResourceNotFoundException(
                "Rider not associated with user with id: "+user.getId()
        ));
    }
}
