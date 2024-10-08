package com.application.uberApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverDTO {
    private Long id;
    private UserDTO user;
    private Double rating;
    private Boolean available;
    private String vehicleId;
}
