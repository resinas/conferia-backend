package org.example.services;


import org.example.dto.GetUserResponse;
import org.example.dto.UpdateUserRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

public interface UserService {

    UserDetailsService userDetailsService();

    GetUserResponse getModifiedUserDetails(UserDetails userDetails) throws IOException;

}
