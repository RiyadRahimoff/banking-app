package com.azecoders.rrbank.controller;

import com.azecoders.rrbank.model.requests.CreateLoginRequest;
import com.azecoders.rrbank.model.requests.CreateRefreshTokenRequest;
import com.azecoders.rrbank.model.response.LoginResponse;
import com.azecoders.rrbank.service.concrete.AuthServiceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthServiceHandler authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody CreateLoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh")
    public LoginResponse refresh(@RequestBody CreateRefreshTokenRequest request) {
        return authService.refreshToken(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam String email) {
        authService.logout(email);
        return ResponseEntity.ok("Logout successfully");
    }
}
