package com.application.uberApp.services.Impl;

import com.application.uberApp.entities.RideRequest;
import com.application.uberApp.exceptions.ResourceNotFoundException;
import com.application.uberApp.repositories.RideRequestRepository;
import com.application.uberApp.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {
    private final RideRequestRepository rideRequestRepository;
    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId).orElseThrow(()->
                new ResourceNotFoundException("RideRequest not found with Id: "+rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
        rideRequestRepository.findById(rideRequest.getId()).orElseThrow(()->
                new ResourceNotFoundException("Ride request not found with Id: "+rideRequest.getId()));
        rideRequestRepository.save(rideRequest);
    }
}
