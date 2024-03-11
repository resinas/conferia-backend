package org.example.services;

import org.example.dto.*;
import org.example.entities.User;

public interface AuthenticationService {

    User updateCredentials (UpdateRequest updateRequest);

    User register(RegisterRequest registerRequest);

    JwtAuthenticationResponse signin(SigninRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    }
