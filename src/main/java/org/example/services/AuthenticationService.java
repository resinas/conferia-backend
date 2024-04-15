package org.example.services;

import org.example.dto.requests.*;
import org.example.entities.User;

public interface AuthenticationService {

    User register(RegisterRequest registerRequest);

    JwtAuthenticationResponse signin(SigninRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    JwtAuthenticationResponse updateCredentials (String username, UserRequest updateRequest);

    User changePasswordForUser(ChangePasswordRequest changePasswordRequest, String username);

}
