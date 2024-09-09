package icpmapp.services.impl;

import icpmapp.dto.requests.EmailRequest;
import lombok.RequiredArgsConstructor;
import icpmapp.services.EmailService;
import icpmapp.services.JWTService;
import icpmapp.services.UserService;
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
        message.setFrom("noreply@icpm.compute.dtu.dk");
        message.setTo(emailRequest.getReceiver());
        message.setSubject("ICPM app account activation");
        String token =  jwtService.generateToken(userDetails);
        message.setText("To activate your account for the ICPM app, click on the following link: https://localhost:5173/icpm-navigator/#/auth/register/" + token);
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
        message.setText("Use this link to reset your password: https://localhost:5173/icpm-navigator/#/auth/login/resetpassword/" + token);
        mailSender.send(message);
    }
}
