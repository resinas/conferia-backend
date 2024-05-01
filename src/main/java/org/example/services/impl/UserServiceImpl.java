package org.example.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.example.dto.responses.GetUserResponse;
import org.example.entities.GalleryImage;
import org.example.entities.User;
import org.example.repository.GalleryStorageRepository;
import org.example.repository.UserRepository;
import org.example.services.StorageService;
import org.example.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GalleryStorageRepository galleryStorageRepository;

    private final StorageService storageService;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with email " + username));
            }
        };
    }

    public GetUserResponse getModifiedUserDetails(UserDetails userDetails) throws IOException {
        if (userDetails instanceof User user) {
            GetUserResponse getUserResponse = new GetUserResponse();
            getUserResponse.setEmail(user.getEmail());
            getUserResponse.setFirstname(user.getFirstname());
            getUserResponse.setLastname(user.getLastname());
            getUserResponse.setCompany(user.getCompany());
            getUserResponse.setCountry(user.getCountry());
            getUserResponse.setSharingChoice(user.getSharingchoice());
            getUserResponse.setProfilePicture(user.getAvatar_path());
            getUserResponse.setId(user.getId());
            return getUserResponse;
        }
        throw new IllegalArgumentException("The provided userDetails cannot be cast to User");
    }

    @Transactional
    public void ChangeLikeStatusForGalleryImage(Boolean likes, String username, String filePath) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + username));

        Optional<GalleryImage> galleryImage = galleryStorageRepository.findByPath(filePath);
        System.out.println("this is the filepath: " + filePath);
        System.out.println("this is likes: " + likes);

        if (galleryImage.isPresent()) {
            System.out.println("After isPresent");
            GalleryImage image = galleryImage.get();

            if (likes) {
                if (!image.getLikedBy().contains(user)) {
                    System.out.println("The picture is liked");
                    image.getLikedBy().add(user);
                    user.getLikes().add(image);
                }
            } else {
                System.out.println("The picture is not liked");
                image.getLikedBy().remove(user);
                user.getLikes().remove(image);
            }
            userRepository.save(user);
            galleryStorageRepository.save(image);
        }


    }



}
