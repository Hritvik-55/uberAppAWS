package com.application.uberApp.strategies;

import com.application.uberApp.strategies.Impl.DriverMatchingHighestRated;
import com.application.uberApp.strategies.Impl.DriverMatchingNearestDriver;
import com.application.uberApp.strategies.Impl.RideFareDefaultFareCalculationStrategy;
import com.application.uberApp.strategies.Impl.RideFareSurgeFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {
    private final DriverMatchingHighestRated driverMatchingHighestRated;
    private final DriverMatchingNearestDriver driverMatchingNearestDriver;
    private final RideFareDefaultFareCalculationStrategy rideFareDefaultFareCalculationStrategy;
    private final RideFareSurgeFareCalculationStrategy rideFareSurgeFareCalculationStrategy;

    public DriverMatchingStartegy driverMatchingStartegy(double riderRating){
        if(riderRating>=4.5){
            return driverMatchingHighestRated;
        }
        else return driverMatchingNearestDriver;
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy(){
        LocalTime surgeStartTime=LocalTime.of(19,30);
        LocalTime surgeEndTime=LocalTime.of(7,0);
        LocalTime currentTime=LocalTime.now();
        boolean isSurge=currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);
        if(isSurge){
            return rideFareSurgeFareCalculationStrategy;
        }
        else return rideFareDefaultFareCalculationStrategy;
    }

}
