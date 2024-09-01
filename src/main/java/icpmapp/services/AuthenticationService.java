package icpmapp.services;

import icpmapp.dto.requests.*;
import icpmapp.entities.User;
import org.example.dto.requests.*;

public interface AuthenticationService {

    User register(RegisterRequest registerRequest);

    String deleteAccount(RegisterRequest registerRequest);

    JwtAuthenticationResponse signin(SigninRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    JwtAuthenticationResponse updateCredentials (String username, UserRequest updateRequest);

    User changePasswordForUser(ChangePasswordRequest changePasswordRequest, String username);

}
