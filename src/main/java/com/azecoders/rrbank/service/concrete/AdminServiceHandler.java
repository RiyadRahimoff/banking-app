package com.azecoders.rrbank.service.concrete;

import com.azecoders.rrbank.dao.entity.BankAccountEntity;
import com.azecoders.rrbank.dao.entity.CardEntity;
import com.azecoders.rrbank.dao.entity.UserEntity;
import com.azecoders.rrbank.dao.repository.AdminRepository;
import com.azecoders.rrbank.dao.repository.BankAccountRepository;
import com.azecoders.rrbank.dao.repository.CardRepository;
import com.azecoders.rrbank.dao.repository.UserRepository;
import com.azecoders.rrbank.exception.AccountFoundException;
import com.azecoders.rrbank.exception.CardFoundException;
import com.azecoders.rrbank.model.enums.CardStatus;
import com.azecoders.rrbank.model.enums.OrderStatus;
import com.azecoders.rrbank.model.enums.UserStatus;
import com.azecoders.rrbank.model.response.AccountResponse;
import com.azecoders.rrbank.service.abstraction.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSendException;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE)
public class AdminServiceHandler implements AdminService {
    final AdminRepository adminRepository;
    final UserRepository userRepository;
    final MailServiceHandler mailServiceHandler;
    final BankAccountRepository accountRepository;
    private final CardRepository cardRepository;
    @Autowired
    private TaskScheduler taskScheduler;

    @Override
    public List<AccountResponse> allPendingAccounts() {
        return adminRepository.findAllByAccountStatus(OrderStatus.PENDING)
                .stream()
                .map(acc -> AccountResponse.builder()
                        .id(acc.getId())
                        .userId(acc.getUser().getId())
                        .fullName(acc.getUser().getFullName())
                        .email(acc.getUser().getEmail())
                        .phoneNumber(acc.getUser().getPhoneNumber())
                        .prefixs(String.valueOf(acc.getUser().getPhonePrefix()))
                        .isVerified(acc.getUser().isVerified())
                        .userStatus(acc.getUser().getUserStatus())
                        .accountNumber(acc.getAccountNumber())
                        .balance(acc.getBalance())
                        .orderStatus(acc.getAccountStatus())
                        .accountType(acc.getAccountType())
                        .build()
                )
                .toList();
    }

    @Override
    public String unblockAccount(String nationalID) {
        UserEntity user = userRepository.findByNationalId(nationalID)
                .orElseThrow(() -> new AccountFoundException("Account not found", HttpStatus.BAD_REQUEST));

        user.setUserStatus(UserStatus.ACTIVE);

        userRepository.save(user);
        try {
            mailServiceHandler.sendAccountUnblockedMessage(user.getEmail(), user.getFullName());
        } catch (MailSendException e) {
            throw new MailSendException("Mail cannot be send");
        }


        return "Account unblocked by admin";
    }

    @Override
    public String approveAccountOrder(Long id) {
        BankAccountEntity account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountFoundException("Account not found!", HttpStatus.NOT_FOUND));
        account.setAccountStatus(OrderStatus.APPROVED);
        accountRepository.save(account);
        try {
            mailServiceHandler.sendAccountAcceptStatusMessage(account.getUser().getEmail(), account.getUser().getFullName());
        } catch (MailSendException e) {
            throw new MailSendException("Mail cannot be send");
        }
        return "Account created!";
    }

    @Override
    public void approveCardOrder(Long id) {
        CardEntity card = cardRepository.findById(id)
                .orElseThrow(() -> new CardFoundException("Card not found", HttpStatus.NOT_FOUND));
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                card.setCardStatus(CardStatus.ACTIVE);
                cardRepository.save(card);
            }
        };
        timer.schedule(timerTask,18000);

    }

    @Override
    public String rejectAccountOrder(Long id) {
        BankAccountEntity account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountFoundException("Account not found!", HttpStatus.NOT_FOUND));
        account.setAccountStatus(OrderStatus.REJECTED);
        accountRepository.save(account);
        try {
            mailServiceHandler.sendAccountAcceptStatusMessage(account.getUser().getEmail(), account.getUser().getFullName());
        } catch (MailSendException e) {
            throw new MailSendException("Mail cannot be send");
        }
        return "Account rejected!";
    }
}
