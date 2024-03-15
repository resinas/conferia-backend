package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserRequest;
import org.example.entities.User;
import org.example.services.AuthenticationService;
import org.example.services.JWTService;
import org.example.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inactive")
@RequiredArgsConstructor
public class InactiveController {

    private final AuthenticationService authenticationService;
    private final JWTService jwtService;
    private final UserService userService;
    @PostMapping("/setUser")
    public ResponseEntity<User> createUser(@RequestBody UserRequest updateRequest) {
        return ResponseEntity.ok(authenticationService.updateCredentials(updateRequest));
    }

    @GetMapping("/getUser")
    public ResponseEntity<UserDetails> getUserDetails(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUserName(token);
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(username);
        return ResponseEntity.ok(userDetails);
    }
}
