package org.example.services;


import org.example.dto.responses.GetUserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

public interface UserService {

    UserDetailsService userDetailsService();

    GetUserResponse getModifiedUserDetails(UserDetails userDetails) throws IOException;

    void ChangeLikeStatusForGalleryImage(Boolean likes, String username, String filePath);

}
