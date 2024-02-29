package org.example.services;

import org.example.dto.JwtAuthenticationResponse;
import org.example.dto.RefreshTokenRequest;
import org.example.dto.SignUpRequest;
import org.example.dto.SigninRequest;
import org.example.entities.User;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signin(SigninRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    }
