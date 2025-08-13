package com.azecoders.rrbank.controller;

import com.azecoders.rrbank.model.requests.CreateRegisterRequest;
import com.azecoders.rrbank.model.response.RegisterResponse;
import com.azecoders.rrbank.service.concrete.RegisterServiceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterServiceHandler registerService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public RegisterResponse registerUser(@RequestBody CreateRegisterRequest registerRequest) {
        return registerService.registerUser(registerRequest);
    }

    @PostMapping("/verify")
    @ResponseStatus(HttpStatus.OK)
    public String verifyUser(@RequestParam String otpCode) {
        return registerService.verifyUser(otpCode);
    }
}
