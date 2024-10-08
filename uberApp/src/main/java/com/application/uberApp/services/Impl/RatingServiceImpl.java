package com.application.uberApp.services.Impl;

import com.application.uberApp.dto.DriverDTO;
import com.application.uberApp.dto.RiderDTO;
import com.application.uberApp.entities.Driver;
import com.application.uberApp.entities.Rating;
import com.application.uberApp.entities.Ride;
import com.application.uberApp.entities.Rider;
import com.application.uberApp.exceptions.ResourceNotFoundException;
import com.application.uberApp.exceptions.RuntimeConflictException;
import com.application.uberApp.repositories.DriverRepository;
import com.application.uberApp.repositories.RatingRepo;
import com.application.uberApp.repositories.RiderRepository;
import com.application.uberApp.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService{
    private final RatingRepo ratingRepo;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper mapper;

    @Override
    public DriverDTO rateDriver(Ride ride, Integer rating) {
        Driver driver=ride.getDriver();
        Rating ratingObj=ratingRepo.findByRide(ride)
                .orElseThrow(()-> new ResourceNotFoundException("Rating not found with ride Id: "+ride.getId()));
        if(ratingObj.getDriverRating()!=null) throw new RuntimeConflictException("Driver has already been rated! Cannot rate again.");
        ratingObj.setDriverRating(rating);
        ratingRepo.save(ratingObj);
        Double newRating = ratingRepo.findByDriver(driver)
                .stream().mapToDouble(Rating::getDriverRating).average().orElse(0.0);
        driver.setRating(newRating);
        Driver saved = driverRepository.save(driver);
        return mapper.map(saved, DriverDTO.class);

    }

    @Override
    public RiderDTO rateRider(Ride ride, Integer rating) {
        Rider rider=ride.getRider();
        Rating ratingObj=ratingRepo.findByRide(ride)
                .orElseThrow(()-> new ResourceNotFoundException("Rating not found with ride Id: "+ride.getId()));
        if(ratingObj.getRiderRating()!=null) throw new RuntimeConflictException("Rider has already been rated! Cannot rate again.");

        ratingObj.setRiderRating(rating);
        ratingRepo.save(ratingObj);
        Double newRating = ratingRepo.findByRider(rider)
                .stream().mapToDouble(Rating::getRiderRating).average().orElse(0.0);
        rider.setRating(newRating);
        Rider savedRider = riderRepository.save(rider);
        return mapper.map(savedRider, RiderDTO.class);

    }

    @Override
    public void createNewRating(Ride ride) {
        Rating rating=Rating.builder()
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .ride(ride)
                .build();
        ratingRepo.save(rating);
    }
}
