package org.example.services;


import org.example.dto.responses.GetUserResponse;
import org.example.dto.responses.UserIdResponse;
import org.example.dto.responses.getNameResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();

    GetUserResponse getModifiedUserDetails(UserDetails userDetails);

    UserIdResponse getId(String userName);

    getNameResponse getName(int id);

    getNameResponse getName(String username);
}
