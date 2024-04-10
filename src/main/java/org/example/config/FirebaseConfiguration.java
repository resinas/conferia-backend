package org.example.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class FirebaseConfiguration {
    @PostConstruct
    public void initialize() {
        try{
            GoogleCredentials credentials = GoogleCredentials.fromStream(new ClassPathResource("icpm-conference-ad251-firebase-adminsdk-apsls-de3033cd9e.json").getInputStream());
            FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(credentials).build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }


        } catch(IOException e) {
            System.err.println("Failed to initialize Firebase application");
        }
    }
}
