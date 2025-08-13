package com.azecoders.rrbank.service.concrete;

import com.azecoders.rrbank.service.abstraction.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceHandler implements MailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendVerificationCode(String to, String code, String fullName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("RR bank account confirmation code");
        message.setText("Hello " + fullName + "\n\n" + "Your confirmation code: " + code);

        try {
            mailSender.send(message);

        } catch (Exception ex) {
            throw new MailSendException("Mail system not working!");
        }
    }

    @Override
    public void sendAccountConfirmedMessage(String to, String fullName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setText("Dear "+fullName+" your account confirmed successfully.Please submit your other information");
        try {
            mailSender.send(message);
        }catch (Exception exception){
            throw new MailSendException("Mail system not working!");
        }
    }

    @Override
    public void sendPasswordResetMessage(String to,String fullName){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setText(fullName+" your password changed.If you don't change report us about this.");
        try {
            mailSender.send(message);
        }catch (Exception exception){
            throw new MailSendException("Mail system not working!");
        }
    }

}
