package org.example.services.impl;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import lombok.RequiredArgsConstructor;
import org.example.entities.User;
import org.example.repository.UserRepository;
import org.example.services.FirebaseService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class FirebaseServiceImpl implements FirebaseService {

    private final UserRepository userRepository;
    public URL generateUploadSignedUrl(String bucketName, String username) throws IOException {

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + username));
        String objectName = "profilePicture/" + user.getId() + ".jpeg";

        user.setProfileUrl(objectName);
        userRepository.save(user);

        Storage storage = StorageOptions.newBuilder().setCredentials(GoogleCredentials.fromStream(
                        new ClassPathResource("icpm-conference-ad251-firebase-adminsdk-apsls-de3033cd9e.json").getInputStream()))
                .build().getService();

        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();

        // Generate a signed URL that allows an object to be uploaded
        return storage.signUrl(
                blobInfo,
                15,
                TimeUnit.MINUTES,
                Storage.SignUrlOption.httpMethod(HttpMethod.PUT),
                Storage.SignUrlOption.withContentType());
    }

    public URL generateRetrieveSignedUrl(String bucketName, String username) throws IOException {

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + username));
        String objectName = "profilePicture/" + user.getId() + ".webp";

        user.setProfileUrl(objectName);

        Storage storage = StorageOptions.newBuilder().setCredentials(GoogleCredentials.fromStream(
                        new ClassPathResource("icpm-conference-ad251-firebase-adminsdk-apsls-de3033cd9e.json").getInputStream()))
                .build().getService();

        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        // Generate a signed URL that allows an object to be retrieved
        return storage.signUrl(
                blobInfo,
                15,
                TimeUnit.MINUTES,
                Storage.SignUrlOption.httpMethod(HttpMethod.GET));
    }

}
