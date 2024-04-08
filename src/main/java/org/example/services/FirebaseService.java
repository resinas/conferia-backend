package org.example.services;

import java.io.IOException;
import java.net.URL;

public interface FirebaseService {
    URL generateUploadSignedUrl(String bucketName, String objectName) throws IOException;

    URL generateRetrieveSignedUrl(String bucketName, String username) throws IOException;
}
