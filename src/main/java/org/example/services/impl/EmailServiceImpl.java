package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.EmailRequest;
import org.example.services.EmailService;
import org.example.services.JWTService;
import org.example.services.UserService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender mailSender;
    private final JWTService jwtService;
    private final UserService userService;

    public void sendSignup(EmailRequest emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("auth@ICPM24.com");
        message.setTo(emailRequest.getReceiver());
        message.setSubject("Authorization Email");
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(emailRequest.getReceiver());
        String token =  jwtService.generateToken(userDetails);
        message.setText("Here is your setup link: http://localhost:5173/icpm-navigator/#/auth/register/" + token);
        mailSender.send(message);
    }
}
