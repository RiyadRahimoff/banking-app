package com.azecoders.rrbank.service.concrete;

import com.azecoders.rrbank.dao.entity.BankAccountEntity;
import com.azecoders.rrbank.dao.entity.UserEntity;
import com.azecoders.rrbank.dao.repository.AdminRepository;
import com.azecoders.rrbank.dao.repository.BankAccountRepository;
import com.azecoders.rrbank.dao.repository.UserRepository;
import com.azecoders.rrbank.exception.AccountFoundException;
import com.azecoders.rrbank.model.enums.OrderStatus;
import com.azecoders.rrbank.model.enums.UserStatus;
import com.azecoders.rrbank.service.abstraction.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE)
public class AdminServiceHandler implements AdminService {
    final AdminRepository adminRepository;
    final UserRepository userRepository;
    final MailServiceHandler mailServiceHandler;
    final BankAccountRepository accountRepository;

    @Override
    public List<BankAccountEntity> allPendingAccounts() {
        return adminRepository.findAllByAccountStatus(OrderStatus.PENDING);
    }

    @Override
    public String unblockAccount(String nationalID) {
        UserEntity user = userRepository.findByNationalId(nationalID)
                .orElseThrow(() -> new AccountFoundException("Account not found", HttpStatus.BAD_REQUEST));

        user.setUserStatus(UserStatus.ACTIVE);

       userRepository.save(user);
       try{
           mailServiceHandler.sendAccountUnblockedMessage(user.getEmail(), user.getFullName());
       } catch (MailSendException e) {
           throw new MailSendException("Mail cannot be send");
       }


        return "Account unblocked by admin";
    }

    @Override
    public String acceptAccountOrder(Long id) {
        BankAccountEntity account = accountRepository.findById(id)
                .orElseThrow(()->new AccountFoundException("Account not found!",HttpStatus.NOT_FOUND));
        account.setAccountStatus(OrderStatus.APPROVED);
        accountRepository.save(account);
        try{
           mailServiceHandler.sendAccountAcceptStatusMessage(account.getUser().getEmail(),account.getUser().getFullName());
        } catch (MailSendException e) {
            throw new MailSendException("Mail cannot be send");
        }
        return "Account created!";
    }

    @Override
    public String rejectAccountOrder(Long id) {
        BankAccountEntity account = accountRepository.findById(id)
                .orElseThrow(()->new AccountFoundException("Account not found!",HttpStatus.NOT_FOUND));
        account.setAccountStatus(OrderStatus.REJECTED);
        accountRepository.save(account);
        try{
            mailServiceHandler.sendAccountAcceptStatusMessage(account.getUser().getEmail(),account.getUser().getFullName());
        } catch (MailSendException e) {
            throw new MailSendException("Mail cannot be send");
        }
        return "Account rejected!";
    }
}
