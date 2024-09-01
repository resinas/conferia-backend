package icpmapp.controller;

import lombok.RequiredArgsConstructor;
import icpmapp.dto.requests.EmailRequest;
import icpmapp.dto.requests.JwtAuthenticationResponse;
import icpmapp.dto.requests.RefreshTokenRequest;
import icpmapp.dto.requests.SigninRequest;
import icpmapp.services.AuthenticationService;
import icpmapp.services.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final EmailService emailService;


    //User sign-in, and JWT token management.
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest signinRequest){
        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

    //Email Operations
    @PostMapping("/signup")
    public ResponseEntity<?> sendSignup(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.sendSignup(emailRequest);
            return ResponseEntity.ok().body("signup email sent successfully.");
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send signup email.");
        }
    }
    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.sendResetPassword(emailRequest);
            return ResponseEntity.ok().body("Reset email sent successfully.");
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send reset email.");
        }
    }

}
