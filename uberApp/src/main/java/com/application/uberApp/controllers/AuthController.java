package com.application.uberApp.controllers;

import com.application.uberApp.dto.*;
import com.application.uberApp.services.Impl.AuthServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Stream;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceImpl authService;

    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO){
        return new ResponseEntity<>(authService.signup(signUpDTO), HttpStatus.CREATED) ;
    }

    @PostMapping("/onBoardNewDriver/{userId}")
    @Secured("ROLE_ADMIN")
    ResponseEntity<DriverDTO> onBoardNewDriver(@PathVariable Long userId, @RequestBody OnBoardDriverDTO onBoardDriverDTO){
        return new ResponseEntity<>(authService.onboardDriver(userId,
                onBoardDriverDTO.getVehicleId()),HttpStatus.CREATED);


    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO,
                                                  HttpServletResponse response, HttpServletRequest request){
        String[] tokens = authService.login(loginRequestDTO.getEmail(),loginRequestDTO.getPassword());
        Cookie cookie=new Cookie("token",tokens[1]);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseEntity.ok(new LoginResponseDTO(tokens[0]));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(HttpServletRequest request){
        String token = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()-> new AuthenticationServiceException("Refresh token not found inside the cookies"));
        String accessToken=authService.refreshToken(token);
        return ResponseEntity.ok(new LoginResponseDTO(accessToken));
    }


}
