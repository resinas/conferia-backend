package org.example.services.impl;

import lombok.RequiredArgsConstructor;

import org.example.dto.responses.GetUserResponse;
import org.example.entities.User;
import org.example.repository.GalleryStorageRepository;
import org.example.repository.UserRepository;
import org.example.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with email " + username));
            }
        };
    }

    public GetUserResponse getModifiedUserDetails(UserDetails userDetails) {
        if (userDetails instanceof User user) {
            GetUserResponse getUserResponse = new GetUserResponse();
            getUserResponse.setEmail(user.getEmail());
            getUserResponse.setFirstname(user.getFirstname());
            getUserResponse.setLastname(user.getLastname());
            getUserResponse.setCompany(user.getCompany());
            getUserResponse.setCountry(user.getCountry());
            getUserResponse.setSharingChoice(user.getSharingchoice());
            getUserResponse.setProfilePicture(user.getAvatarPath());
            return getUserResponse;
        }
        throw new IllegalArgumentException("The provided userDetails cannot be cast to User");
    }
}
