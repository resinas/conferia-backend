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
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final JWTService jwtService;
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hi User");
    }

    @GetMapping("/settings")
    public ResponseEntity<UserDetails> getUserDetails(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUserName(token);
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(username);
        return ResponseEntity.ok(userDetails);
    }
    @PostMapping("/update")
    public ResponseEntity<User> update(@RequestBody UserRequest updateRequest){
        return ResponseEntity.ok(authenticationService.updateCredentials(updateRequest));
    }
}
