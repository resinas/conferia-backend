package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.*;
import org.example.entities.Role;
import org.example.entities.User;
import org.example.repository.UserRepository;
import org.example.services.AuthenticationService;
import org.example.services.EmailService;
import org.example.services.JWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    public User register(RegisterRequest registerRequest){
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setFirstname(registerRequest.getFirstname());
        user.setLastname(registerRequest.getLastname());
        user.setRole(Role.INACTIVE);

        return userRepository.save(user);
    }

    public JwtAuthenticationResponse signin(SigninRequest signinRequest){
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),
                    signinRequest.getPassword()));

            var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(() ->
                    new IllegalArgumentException("invalid email or password."));
            var jwt = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setAccessToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshToken);
            return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setAccessToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }

    public User updateCredentials(UserRequest updateRequest){
        User user = userRepository.findByEmail(updateRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + updateRequest.getEmail()));

        user.setPassword(passwordEncoder.encode(updateRequest.getPassword())); //passwordEncoder.encode()
        user.setLastname(updateRequest.getLastname());
        user.setFirstname(updateRequest.getFirstname());

        if (user.getRole() == Role.INACTIVE) {
            user.setRole(Role.USER);
        }

        return userRepository.save(user);
    }

    public User resetPassword(ResetPasswordRequest resetPasswordRequest, String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + username));
        user.setPassword(passwordEncoder.encode(resetPasswordRequest.getPassword()));
        return userRepository.save(user);
    }

}
