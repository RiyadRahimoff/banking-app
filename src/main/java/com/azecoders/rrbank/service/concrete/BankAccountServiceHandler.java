package com.azecoders.rrbank.service.concrete;

import com.azecoders.rrbank.dao.entity.BankAccountEntity;
import com.azecoders.rrbank.dao.entity.CardEntity;
import com.azecoders.rrbank.dao.entity.UserEntity;
import com.azecoders.rrbank.dao.repository.BankAccountRepository;
import com.azecoders.rrbank.dao.repository.CardRepository;
import com.azecoders.rrbank.dao.repository.UserRepository;
import com.azecoders.rrbank.exception.AccountFoundException;
import com.azecoders.rrbank.exception.TransactionException;
import com.azecoders.rrbank.exception.UserFoundException;
import com.azecoders.rrbank.mapper.AccountMapper;
import com.azecoders.rrbank.model.requests.CreateAccountRequest;
import com.azecoders.rrbank.model.response.AccountResponse;
import com.azecoders.rrbank.service.abstraction.BankAccountService;
import com.azecoders.rrbank.util.AccountNumberGenerator;
import com.azecoders.rrbank.util.VerificationCodeGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BankAccountServiceHandler implements BankAccountService {
    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;
    private final MailServiceHandler mailServiceHandler;
    private final CardRepository cardRepository;


    @Override
    public BigDecimal getBalance(Long accountId) {
        BankAccountEntity bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new AccountFoundException("Account not found", HttpStatus.BAD_REQUEST));
        return bankAccount.getBalance();
    }

    @Override
    @Transactional
    public String deposit(Long id, BigDecimal deposit) {
        BankAccountEntity accountEntity = bankAccountRepository.findById(id)
                .orElseThrow(() -> new AccountFoundException("Account not found!", HttpStatus.BAD_REQUEST));
        UserEntity user = accountEntity.getUser();
        validateActiveUser(user);
        if (deposit.compareTo(BigDecimal.ZERO) <= 0) {
            throw new TransactionException("Deposit amount must be positive!",HttpStatus.UNPROCESSABLE_ENTITY);
        }
        accountEntity.setBalance(accountEntity.getBalance().add(deposit));
        return "Deposit successful";
    }

    @Override
    @Transactional
    public String withdraw(Long id, BigDecimal amount) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserFoundException("User not found", HttpStatus.BAD_REQUEST));

        validateActiveUser(user);
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new TransactionException("Deposit amount must be positive!",HttpStatus.UNPROCESSABLE_ENTITY);
        }

        BankAccountEntity account = bankAccountRepository.findById(user.getId())
                .orElseThrow(() -> new AccountFoundException("Account not found", HttpStatus.BAD_REQUEST));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new TransactionException("Insufficient balance",HttpStatus.UNPROCESSABLE_ENTITY);
        }

        String newOtpCode = VerificationCodeGenerator.generateCode();
        mailServiceHandler.sendVerificationCode(user.getEmail(), newOtpCode, user.getFullName());
        user.setPendingWithdrawAmount(amount);

        user.setOtpCode(newOtpCode);
        userRepository.save(user);
        return "OTP sent to email. Please verify to complete withdraw.";
    }

    @Override
    @Transactional
    public String confirmWithdraw(Long id, String otpCode) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserFoundException("User not found", HttpStatus.BAD_REQUEST));
        validateActiveUser(user);
        if (!otpCode.equals(user.getOtpCode())) {
            throw new RuntimeException("Invalid OTP");
        }
        BankAccountEntity account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new AccountFoundException("Account not found", HttpStatus.BAD_REQUEST));

        account.setBalance(account.getBalance().subtract(user.getPendingWithdrawAmount()));
        user.setOtpCode(null);
        userRepository.save(user);

        return "Withdraw succesfully";
    }

    @Override
    public void transfer(String fromCardNumber, String toCardNumber, BigDecimal amount) {
        CardEntity fromCard = cardRepository.findByCardNumber(fromCardNumber)
                .orElseThrow(() -> new RuntimeException("From card not found"));

        CardEntity toCard = cardRepository.findByCardNumber(toCardNumber)
                .orElseThrow(() -> new RuntimeException("To card not found"));

        BankAccountEntity fromAccount = fromCard.getAccount();
        BankAccountEntity toAccount = toCard.getAccount();

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        bankAccountRepository.save(fromAccount);
        bankAccountRepository.save(toAccount);
    }

    @Override
    public BankAccountEntity getAccountDetails(Long accountId) {
        return null;
    }

    @Override
    public void closeAccount(Long accountId) {

    }

    @Override
    public void freezeAccount(Long accountId) {

    }

    private void validateActiveUser(UserEntity user) {
        if (!"ACTIVE".equalsIgnoreCase(String.valueOf(user.getUserStatus()))) {
            throw new TransactionException("User account is not active!", HttpStatus.FORBIDDEN);
        }
    }
}
