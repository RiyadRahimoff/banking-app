package com.azecoders.rrbank.controller;

import com.azecoders.rrbank.model.requests.CreateLoginRequest;
import com.azecoders.rrbank.model.requests.CreateRefreshTokenRequest;
import com.azecoders.rrbank.model.response.LoginResponse;
import com.azecoders.rrbank.service.concrete.AuthServiceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthServiceHandler authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody CreateLoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse refresh(@RequestBody CreateRefreshTokenRequest request) {
        return authService.refreshToken(request);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> logout(@RequestParam String email) {
        authService.logout(email);
        return ResponseEntity.ok("Logout successfully");
    }

    @PostMapping("/reset-password")
    @ResponseStatus(HttpStatus.OK)
    public void resetPassword(@RequestParam String email) {
        authService.resetPassword(email);
    }

    @PostMapping("/update-password")
    @ResponseStatus(HttpStatus.OK)
    public String verifyReset(@RequestParam String verificationCode, @RequestParam String newPassword) {
        return authService.verifyReset(verificationCode, newPassword);
    }
}
