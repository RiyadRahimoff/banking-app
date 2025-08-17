package com.azecoders.rrbank.service.abstraction;

import com.azecoders.rrbank.dao.entity.BankAccountEntity;
import com.azecoders.rrbank.model.requests.CreateAccountRequest;
import com.azecoders.rrbank.model.response.AccountResponse;

import java.math.BigDecimal;

public interface BankAccountService {
    AccountResponse createAccount(CreateAccountRequest accountRequest);

    BigDecimal getBalance(Long accountId);

    String deposit(Long id, BigDecimal deposit);

    String withdraw(Long id, BigDecimal amount);

    String confirmWithdraw(Long id, String otpCode);

    void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount);

    BankAccountEntity getAccountDetails(Long accountId);

    void closeAccount(Long accountId);

    void freezeAccount(Long accountId);


}
