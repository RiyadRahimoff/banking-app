package com.azecoders.rrbank.service.abstraction;

import com.azecoders.rrbank.model.requests.CreateAccountRequest;
import com.azecoders.rrbank.model.response.AccountResponse;

public interface BankAccountService {
   AccountResponse createAccount(CreateAccountRequest accountRequest);

}
