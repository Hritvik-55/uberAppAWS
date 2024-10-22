package com.application.uberApp.entities;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(indexes = {
        @Index(name = "idx_driver_vehicle_id",columnList = "vehicleId")
})

public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double rating;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Boolean available;
    private String vehicleId;
    @Column(columnDefinition = "Geometry(Point,4326)")
    private Point currentLocation;
}