package icpmapp.services;

import icpmapp.dto.requests.EmailRequest;

import java.nio.file.AccessDeniedException;

public interface EmailService {
    void sendSignup(EmailRequest emailRequest) throws AccessDeniedException;

   void sendResetPassword(EmailRequest emailRequest) throws AccessDeniedException;
}
