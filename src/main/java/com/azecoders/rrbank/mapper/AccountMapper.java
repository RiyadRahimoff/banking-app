package com.azecoders.rrbank.mapper;

import com.azecoders.rrbank.dao.entity.BankAccountEntity;
import com.azecoders.rrbank.model.requests.CreateAccountRequest;

public class AccountMapper {
    public static BankAccountEntity toEntity(CreateAccountRequest createAccountRequest){
        return BankAccountEntity.builder()
                .balance(createAccountRequest.getBalance())
                .accountType(createAccountRequest.getAccountType())
                .build();
    }
}
