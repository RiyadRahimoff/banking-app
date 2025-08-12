package com.azecoders.rrbank.service.concrete;

import com.azecoders.rrbank.dao.entity.UserEntity;
import com.azecoders.rrbank.dao.repository.UserRepository;
import com.azecoders.rrbank.exception.UserFoundException;
import com.azecoders.rrbank.mapper.UserMapper;
import com.azecoders.rrbank.model.enums.UserStatus;
import com.azecoders.rrbank.model.requests.CreateRegisterRequest;
import com.azecoders.rrbank.model.response.RegisterResponse;
import com.azecoders.rrbank.service.abstraction.RegisterService;
import com.azecoders.rrbank.util.VerificationCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSendException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.azecoders.rrbank.model.enums.ExceptionEnums.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class RegisterServiceHandler implements RegisterService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailServiceHandler mailService;

    @Override
    public RegisterResponse registerUser(CreateRegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new UserFoundException(USER_NOT_FOUND.getCode(), USER_NOT_FOUND.getMessage());
        }
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        String otpCode = VerificationCodeGenerator.generateCode();
        UserEntity user = UserMapper.toEntity(registerRequest, encodedPassword, otpCode);
        userRepository.save(user);

        try {
            mailService.sendVerificationCode(user.getEmail(), otpCode, user.getFullName());
        } catch (MailSendException e) {
            throw new RuntimeException("OTP message cannot be send please try again!");
        }

        return UserMapper.toResponse(user);
    }

    @Override
    public boolean verifyuser(String otpCode) {
        UserEntity user = userRepository.findByOtpCode(otpCode)
                .orElseThrow(() -> new MailSendException("Valid or expired code"));

        user.setUserStatus(UserStatus.ACTIVE);
        user.setVerified(true);
        user.setOtpCode(null);
        userRepository.save(user);
        try {
            mailService.sendAccountConfirmedMessage(user.getEmail(), user.getFullName());
        } catch (MailSendException e) {
            throw new MailSendException("OTP message cannot be send please try again!");
        }
        return true;


    }
}
