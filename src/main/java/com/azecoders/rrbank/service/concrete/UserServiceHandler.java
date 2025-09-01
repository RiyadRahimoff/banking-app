package com.azecoders.rrbank.service.concrete;

import com.azecoders.rrbank.dao.entity.BankAccountEntity;
import com.azecoders.rrbank.dao.entity.UserEntity;
import com.azecoders.rrbank.dao.repository.AccountRepository;
import com.azecoders.rrbank.dao.repository.UserRepository;
import com.azecoders.rrbank.exception.AccountFoundException;
import com.azecoders.rrbank.exception.UserFoundException;
import com.azecoders.rrbank.mapper.AccountMapper;
import com.azecoders.rrbank.model.enums.OrderStatus;
import com.azecoders.rrbank.model.enums.UserStatus;
import com.azecoders.rrbank.model.requests.CreateAccountRequest;
import com.azecoders.rrbank.model.requests.CreateUserInformationRequest;
import com.azecoders.rrbank.service.abstraction.UserService;
import com.azecoders.rrbank.util.AccountNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserServiceHandler implements UserService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Override
    public void updateInfo(CreateUserInformationRequest createUserInformationRequest, String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserFoundException("User not found:" + email, HttpStatus.BAD_REQUEST));

        if (user.getUserStatus() != UserStatus.ACTIVE){
            throw new AccountFoundException("Account is not verified!",HttpStatus.BAD_REQUEST);
        }
            user.setAddress(createUserInformationRequest.getAdress());

        LocalDate birthDate = createUserInformationRequest.getBirthDate();

        if (birthDate.plusYears(18).isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("User must be at least 18 years old");
        } else {
            user.setBirthDate(birthDate);
        }
        user.setNationalId(createUserInformationRequest.getNationalId());
        userRepository.save(user);
    }

    @Override
    public String orderAccount(CreateAccountRequest accountRequest,String email) {
        UserEntity user =userRepository.findByEmail(email)
                .orElseThrow(()->new AccountFoundException("Account not found",HttpStatus.CONFLICT));

        BankAccountEntity accountEntity = AccountMapper.toEntity(accountRequest);
        accountEntity.setAccountType(accountRequest.getAccountType());
        accountEntity.setBalance(BigDecimal.ZERO);
        accountEntity.setAccountStatus(OrderStatus.PENDING);
        accountEntity.setAccountNumber(AccountNumberGenerator.generateMasterCard());
        accountEntity.setUser(user);

        accountRepository.save(accountEntity);

        return "Your account request has been submitted and is pending admin approval.";
    }
}
