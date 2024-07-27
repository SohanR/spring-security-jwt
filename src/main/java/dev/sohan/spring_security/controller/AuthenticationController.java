package dev.sohan.spring_security.controller;

import dev.sohan.spring_security.dto.JwtAuthenticationResponse;
import dev.sohan.spring_security.dto.RefreshTokenRequest;
import dev.sohan.spring_security.dto.SignInRequest;
import dev.sohan.spring_security.dto.SignUpRequest;
import dev.sohan.spring_security.entities.User;
import dev.sohan.spring_security.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello");
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshRequest){
        return  ResponseEntity.ok(authenticationService.refreshToken(refreshRequest));
    }
}