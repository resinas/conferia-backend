package services;

import org.example.services.impl.JWTServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JWTServiceImplTest {

    private JWTServiceImpl jwtService;
    private UserDetails userDetails;

    @BeforeEach
    void setUp(){
        jwtService = new JWTServiceImpl();
        userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("user@email.com");
    }

    @Test
    void generateToken_ShouldGenerateValidToken() {
        String token = jwtService.generateToken(userDetails);
        assertNotNull(token);
    }

    @Test
    void extractUserName_ShouldExtractUsernameFromToken() {
        String token = jwtService.generateToken(userDetails);
        String username = jwtService.extractUserName(token);
        assertEquals(userDetails.getUsername(),username);
    }

    @Test
    void isTokenValid_ShouldReturnTrueForValidToken() {
        String token = jwtService.generateToken(userDetails);
        boolean isValid = jwtService.isTokenValid(token, userDetails);
        assertTrue(isValid);
    }

    @Test
    void isTokenExpired_ShouldReturnFalseForNewlyGeneratedToken() {
        String token = jwtService.generateToken(userDetails);
        // If the isTokenValid method returns true the isTokenExpired method should return false
        assertFalse(jwtService.isTokenValid(token,userDetails));
    }
}
