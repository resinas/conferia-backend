package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.EmailRequest;
import org.example.services.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender mailSender;

    public void sendSignup(EmailRequest emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("auth@ICPM24.com");
        message.setTo(emailRequest.getReceiver());
        message.setSubject("Authorization Email");
        message.setText("Sigurd du er en Mansemand");
        mailSender.send(message);
    }
}
