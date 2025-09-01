package com.azecoders.rrbank.service.concrete;

import com.azecoders.rrbank.service.abstraction.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        message.setText("Dear " + fullName + " your account confirmed successfully.Please submit your other information");
        try {
            mailSender.send(message);
        } catch (Exception exception) {
            throw new MailSendException("Mail system not working!");
        }
    }

    @Override
    public void sendAccountRejectStatusMessage(String to, String fullName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setText(fullName + ",your account request rejected! If you learn your account why rejected you must contact with admin.");
        try {
            mailSender.send(message);
        } catch (Exception exception) {
            throw new MailSendException("Mail system not working!");
        }
    }

    @Override
    public void sendAccountAcceptStatusMessage(String to, String fullName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setText(fullName + ",your account request approved! If you learn your account details you send account details request.");
        try {
            mailSender.send(message);
        } catch (Exception exception) {
            throw new MailSendException("Mail system not working!");
        }
    }

    @Override
    public void sendAccountUnblockedMessage(String to, String fullName) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setText("Dear " + fullName + " your account unblocked.If you don't know your password,you can reset now.");
    }

    @Override
    public void sendPasswordResetMessage(String to, String fullName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setText(fullName + " your password changed.If you don't change report us about this.");
        try {
            mailSender.send(message);
        } catch (Exception exception) {
            throw new MailSendException("Mail system not working!");
        }
    }

}
