package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.RegisterRequest;
import org.example.dto.UserRequest;
import org.example.entities.User;
import org.example.services.AuthenticationService;
import org.example.services.JWTService;
import org.example.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final JWTService jwtService;
    private final UserService userService;
    private final AuthenticationService authenticationService;


    //Basic user interaction and fetching user details:
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hi User");
    }

    @PreAuthorize("hasRole('INACTIVE') or hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/userDetails")
    public ResponseEntity<UserDetails> getUserDetails(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUserName(token);
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(username);
        return ResponseEntity.ok(userDetails);
    }

    //Register new users
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    //Updating credentials:
    @PreAuthorize("hasRole('INACTIVE') or hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody UserRequest updateRequest) {
        return ResponseEntity.ok(authenticationService.updateCredentials(updateRequest));
    }


}
