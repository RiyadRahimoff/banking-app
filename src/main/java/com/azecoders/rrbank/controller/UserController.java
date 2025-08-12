package com.azecoders.rrbank.controller;

import com.azecoders.rrbank.model.requests.CreateUserInformationRequest;
import com.azecoders.rrbank.service.concrete.UserServiceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserServiceHandler serviceHandler;

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateInfo(@RequestBody CreateUserInformationRequest createUserInformationRequest, String email) {
        serviceHandler.updateInfo(createUserInformationRequest, email);
    }
}
