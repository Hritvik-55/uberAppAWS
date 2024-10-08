package com.application.uberApp.services.Impl;

import com.application.uberApp.entities.User;
import com.application.uberApp.exceptions.ResourceNotFoundException;
import com.application.uberApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class UserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElse(null);
    }
    public User getUserById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found with Id: "+userId));

    }
}
