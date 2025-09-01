package com.azecoders.rrbank.service.abstraction;

public interface MailService {
    void sendVerificationCode(String to, String code, String fullName);

    void sendAccountConfirmedMessage(String to, String fullName);

    void sendAccountAcceptStatusMessage(String to, String fullName);

    void sendAccountRejectStatusMessage(String to, String fullName);

    void sendAccountUnblockedMessage(String to,String fullName);

    void sendPasswordResetMessage(String to,String fullName);
}
