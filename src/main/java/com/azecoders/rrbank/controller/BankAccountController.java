package com.azecoders.rrbank.controller;

import com.azecoders.rrbank.model.requests.CreateAccountRequest;
import com.azecoders.rrbank.model.response.AccountResponse;
import com.azecoders.rrbank.service.concrete.BankAccountServiceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class BankAccountController {
    private final BankAccountServiceHandler serviceHandler;
    @PostMapping("/create-account")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountResponse createAccount(CreateAccountRequest accountRequest) {
        return serviceHandler.createAccount(accountRequest);
    }
}
