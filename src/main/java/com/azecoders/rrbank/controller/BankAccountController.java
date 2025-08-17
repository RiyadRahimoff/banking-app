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

import java.math.BigDecimal;

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

    @PostMapping("/balance")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getBalance(Long accountId) {
        return serviceHandler.getBalance(accountId);
    }

    @PostMapping("/deposit")
    @ResponseStatus(HttpStatus.OK)
    public String deposit(Long id, BigDecimal deposit) {
        return serviceHandler.deposit(id, deposit);
    }

    @PostMapping("/withdraw")
    @ResponseStatus(HttpStatus.CREATED)
    public String withdraw(Long id, BigDecimal amount) {
        return serviceHandler.withdraw(id, amount);
    }

    @PostMapping("/confirm-withdraw")
    @ResponseStatus(HttpStatus.CREATED)
    public String confirmWithdraw(Long id, String otpCode) {
        return serviceHandler.confirmWithdraw(id, otpCode);
    }
}
