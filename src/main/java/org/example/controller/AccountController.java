package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.requests.ChangePasswordRequest;
import org.example.dto.requests.JwtAuthenticationResponse;
import org.example.dto.requests.RegisterRequest;
import org.example.dto.requests.UserRequest;
import org.example.dto.responses.GetUserResponse;
import org.example.entities.User;
import org.example.services.AuthenticationService;
import org.example.services.StorageService;
import org.example.services.JWTService;
import org.example.services.UserService;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final JWTService jwtService;
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final StorageService storageService;


    //Basic user interaction and fetching user details:
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hi User");
    }

    @PreAuthorize("hasAnyAuthority('INACTIVE', 'USER', 'ADMIN')")
    @GetMapping("/userDetails")
    public ResponseEntity<GetUserResponse> getUserDetails(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) throws IOException {
        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUserName(token);
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(username);
        return ResponseEntity.ok(userService.getModifiedUserDetails(userDetails));
    }

    //Register new users
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    //Updating credentials:
    @PreAuthorize("hasAnyAuthority('INACTIVE', 'USER', 'ADMIN')")
    @PostMapping("/update")
    public ResponseEntity<JwtAuthenticationResponse> updateUser(@RequestHeader(value = "Authorization", required = false) String authorizationHeader, @RequestBody UserRequest updateRequest) {
        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUserName(token);
        return ResponseEntity.ok(authenticationService.updateCredentials(username, updateRequest));
    }

    //Change password
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestHeader(value = "Authorization", required = false) String authorizationHeader, @RequestBody ChangePasswordRequest changePasswordRequest) {
        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUserName(token);
        authenticationService.changePasswordForUser(changePasswordRequest, username);
        return ResponseEntity.ok("Password has been changed successfully");
    }

    // Change profile picture
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/uploadProfilePicture")
    public ResponseEntity<String> storeFile(@RequestParam("file") MultipartFile file, @RequestHeader(value = "Authorization", required = false) String authorizationHeader) throws IOException {
        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUserName(token);
        storageService.storeProfileImage(file, username);
        return ResponseEntity.ok("File was uploaded successfully");
    }

    // Change profile picture
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/getProfilePicture")
    public ResponseEntity<Resource> getProfilePicture(@RequestHeader(value = "Authorization", required = false) String authorizationHeader, @RequestParam(name = "format") String format) throws IOException {
        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUserName(token);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // or the appropriate content type
                .body(storageService.getProfileImage(username, format));
    }


}
