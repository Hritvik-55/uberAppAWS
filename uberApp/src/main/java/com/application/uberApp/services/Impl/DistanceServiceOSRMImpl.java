package com.application.uberApp.services.Impl;

import com.application.uberApp.services.DistanceService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistanceServiceOSRMImpl implements DistanceService {
//    @Value("${osrm.api}")
    private final String OSRM_API="http://router.project-osrm.org/route/v1/driving/";



    @Override
    public double calculateDistance(Point src, Point destination) {
        try{
            String uri=src.getX()+","+src.getY()+";"+destination.getX()+","+destination.getY();
            OSRMResponseDTO body = RestClient.builder()
                    .baseUrl(OSRM_API)
                    .build()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .body(OSRMResponseDTO.class);
            return body.getRoutes().get(0).getDistance()/1000.0;
        } catch (Exception e) {
            throw new RuntimeException("Error getting data from OSRM "+e.getMessage());
        }

    }
    @Data
    static
    class OSRMResponseDTO{
        private List<OSRMRoute> routes;
    }
    @Data
    static
    class OSRMRoute{
        private Double distance;
    }
}
