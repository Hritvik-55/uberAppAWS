package com.application.uberApp.repositories;

import com.application.uberApp.entities.Driver;
import com.application.uberApp.entities.Rating;
import com.application.uberApp.entities.Ride;
import com.application.uberApp.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepo extends JpaRepository<Rating,Long> {
    List<Rating> findByDriver(Driver driver);
    List<Rating> findByRider(Rider rider);


    Optional<Rating> findByRide(Ride ride);
}
