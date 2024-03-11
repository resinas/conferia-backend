package org.example.services;

import org.example.dto.UpdateRequest;
import org.example.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();
}
