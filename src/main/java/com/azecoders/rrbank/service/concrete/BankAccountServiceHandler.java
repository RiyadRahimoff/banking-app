package com.azecoders.rrbank.service.concrete;

import com.azecoders.rrbank.dao.entity.BankAccountEntity;
import com.azecoders.rrbank.dao.entity.UserEntity;
import com.azecoders.rrbank.dao.repository.BankAccountRepository;
import com.azecoders.rrbank.dao.repository.UserRepository;
import com.azecoders.rrbank.exception.UserFoundException;
import com.azecoders.rrbank.mapper.AccountMapper;
import com.azecoders.rrbank.model.requests.CreateAccountRequest;
import com.azecoders.rrbank.model.response.AccountResponse;
import com.azecoders.rrbank.service.abstraction.BankAccountService;
import com.azecoders.rrbank.util.AccountNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankAccountServiceHandler implements BankAccountService {
    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;
    @Override
    public AccountResponse createAccount(CreateAccountRequest accountRequest) {
        UserEntity user = userRepository.findById(accountRequest.getUserId())
                .orElseThrow(() -> new UserFoundException("User not found", HttpStatus.BAD_REQUEST));


        BankAccountEntity accountEntity = AccountMapper.toEntity(accountRequest);
        accountEntity.setUser(user);
        accountEntity.setAccountNumber(AccountNumberGenerator.generateCode());

        bankAccountRepository.save(accountEntity);

        return AccountResponse.builder()
                .id(accountEntity.getId())
                .accountType(accountEntity.getAccountType())
                .isVerified(user.isVerified())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .prefixs(String.valueOf(user.getPhonePrefix()))
                .phoneNumber(user.getPhoneNumber())
                .balance(accountEntity.getBalance())
                .userId(user.getId())
                .userStatus(user.getUserStatus())
                .accountNumber(accountEntity.getAccountNumber())
                .build();

    }
}
