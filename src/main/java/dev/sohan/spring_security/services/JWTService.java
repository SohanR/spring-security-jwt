package dev.sohan.spring_security.services;

import dev.sohan.spring_security.dto.JwtAuthenticationResponse;
import dev.sohan.spring_security.dto.RefreshTokenRequest;
import dev.sohan.spring_security.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

public interface JWTService {
    String generateToken(String username);

    String  extractUserName(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);


}
