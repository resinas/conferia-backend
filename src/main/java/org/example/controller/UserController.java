package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.SignUpRequest;
import org.example.dto.UpdateRequest;
import org.example.entities.User;
import org.example.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {


    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hi User");
    }
}
