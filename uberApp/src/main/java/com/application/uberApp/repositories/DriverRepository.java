package com.application.uberApp.repositories;

import com.application.uberApp.entities.Driver;
import com.application.uberApp.entities.User;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {
    @Query(value = "SELECT d.*,ST_Distance(d.current_location,:pickUpLocation) AS distance " +
            "FROM driver d " +
            "where d.available=true AND ST_DWithin(d.current_location,:pickUpLocation,10000) " +
            "ORDER BY distance " +
            "LIMIT 10",nativeQuery = true)
    List<Driver> findTenNearestDrivers(Point pickUpLocation);
    @Query(value = "SELECT d.*" +
            "FROM driver d " +
            "where d.available=true and ST_DWithin(d.current_location,:pickupLocation,15000) " +
            "order by d.rating desc " +
            "limit 10",nativeQuery = true)
    List<Driver> findNearbyTenTopRatedDrivers(Point pickupLocation);
    Optional<Driver> findByUser(User user);
}
