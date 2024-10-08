package com.application.uberApp.services;

import com.application.uberApp.dto.DriverDTO;
import com.application.uberApp.dto.SignUpDTO;
import com.application.uberApp.dto.UserDTO;

public interface AuthService {
    String[] login(String email,String password);
    UserDTO signup(SignUpDTO signUpDTO);

    DriverDTO onboardDriver(Long userId,String vehicleId);

    String refreshToken(String token);
}
