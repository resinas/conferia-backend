package org.example.services;

import org.example.dto.*;
import org.example.entities.User;

public interface AuthenticationService {

    User register(RegisterRequest registerRequest);

    JwtAuthenticationResponse signin(SigninRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    User updateCredentials (UserRequest updateRequest);

    }
