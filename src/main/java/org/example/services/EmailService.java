package org.example.services;

import org.example.dto.EmailRequest;

public interface EmailService {
    void sendSignup(EmailRequest emailRequest);
}
