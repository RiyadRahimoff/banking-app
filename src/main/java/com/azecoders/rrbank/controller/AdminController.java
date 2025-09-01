package com.azecoders.rrbank.controller;

import com.azecoders.rrbank.dao.entity.BankAccountEntity;
import com.azecoders.rrbank.model.enums.OrderStatus;
import com.azecoders.rrbank.service.concrete.AdminServiceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminServiceHandler serviceHandler;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/pending-accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<BankAccountEntity> allPendingAccounts() {
        return serviceHandler.allPendingAccounts();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/unblock-account")
    @ResponseStatus(HttpStatus.OK)
    public String unblockAccount(@RequestParam String nationalID) {
        return serviceHandler.unblockAccount(nationalID);
    }

}
