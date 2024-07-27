package dev.sohan.spring_security.services;

import dev.sohan.spring_security.dto.JwtAuthenticationResponse;
import dev.sohan.spring_security.dto.RefreshTokenRequest;
import dev.sohan.spring_security.dto.SignInRequest;
import dev.sohan.spring_security.dto.SignUpRequest;
import dev.sohan.spring_security.entities.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
