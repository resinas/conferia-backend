package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.requests.EmailRequest;
import org.example.services.EmailService;
import org.example.services.JWTService;
import org.example.services.UserService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender mailSender;
    private final JWTService jwtService;
    private final UserService userService;

    public void sendSignup(EmailRequest emailRequest) throws AccessDeniedException {
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(emailRequest.getReceiver());
        boolean hasRequiredRole = userDetails.getAuthorities().stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals("INACTIVE"));


        if (!hasRequiredRole) {
            throw new AccessDeniedException("User already activated.");
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("auth@ICPM24.com");
        message.setTo(emailRequest.getReceiver());
        message.setSubject("Authorization Email");
        String token =  jwtService.generateToken(userDetails);
        message.setText("Here is your setup link: http://localhost:5173/icpm-navigator/#/auth/register/" + token);
        mailSender.send(message);
    }

    public void sendResetPassword(EmailRequest emailRequest) throws AccessDeniedException {
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(emailRequest.getReceiver());
        boolean hasWrongRole = userDetails.getAuthorities().stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals("INACTIVE"));


        if (hasWrongRole) {
            throw new AccessDeniedException("User needs to be activated");
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("auth@ICPM24.com");
        message.setTo(emailRequest.getReceiver());
        message.setSubject("Reset password");
        String token =  jwtService.generateToken(userDetails);
        message.setText("Here is your setup link: http://localhost:5173/icpm-navigator/#/auth/login/resetpassword/" + token);
        mailSender.send(message);
    }
}
