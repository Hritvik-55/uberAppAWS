package com.application.uberApp.services.Impl;

import com.application.uberApp.Security.JwtService;
import com.application.uberApp.dto.DriverDTO;
import com.application.uberApp.dto.SignUpDTO;
import com.application.uberApp.dto.UserDTO;
import com.application.uberApp.entities.Driver;
import com.application.uberApp.entities.User;
import com.application.uberApp.entities.enums.Roles;
import com.application.uberApp.exceptions.ResourceNotFoundException;
import com.application.uberApp.exceptions.RuntimeConflictException;
import com.application.uberApp.repositories.UserRepository;
import com.application.uberApp.services.AuthService;
import com.application.uberApp.services.DriverService;
import com.application.uberApp.services.RiderService;
import com.application.uberApp.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public String[] login(String email, String password) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        User user = (User) authenticate.getPrincipal();
        String accessToken=jwtService.generateToken(user);
        String refreshToken= jwtService.generateRefreshToken(user);
        return new String[]{accessToken,refreshToken};
    }

    @Override
    @Transactional
    public UserDTO signup(SignUpDTO signUpDTO) {
        User user = userRepository.findByEmail(signUpDTO.getEmail()).orElse(null);
        if(user!=null){
            throw new RuntimeConflictException("User already exists with email "+signUpDTO.getEmail());
        }


        User mappedUser=modelMapper.map(signUpDTO,User.class);
        mappedUser.setRole(Set.of(Roles.RIDER));
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        User savedUser=userRepository.save(mappedUser);

        //Create user related entities
        riderService.createNewRider(savedUser);
        // TODO add wallet related service
        walletService.createNewWallet(savedUser);
        return modelMapper.map(savedUser,UserDTO.class);
    }

    @Override
    public DriverDTO onboardDriver(Long userId,String vehicleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with Id:"+userId));
        if(user.getRole().contains(Roles.DRIVER))
            throw new RuntimeConflictException("User with Id: "+userId+" is already a driver");
        Driver driver = Driver.builder()
                .user(user)
                .rating(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();
        user.getRole().add(Roles.DRIVER);
        userRepository.save(user);
        Driver savedDriver = driverService.createNewDriver(driver);
        return modelMapper.map(savedDriver,DriverDTO.class);


    }

    @Override
    public String refreshToken(String token) {
        Long userId = jwtService.getUserIdFromToken(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with Id: " + userId));
        return jwtService.generateRefreshToken(user);
    }
}
