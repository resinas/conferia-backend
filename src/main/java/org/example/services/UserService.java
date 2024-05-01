package org.example.services;


import org.example.dto.responses.GetUserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();

    GetUserResponse getModifiedUserDetails(UserDetails userDetails);

}
