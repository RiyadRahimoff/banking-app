package com.azecoders.rrbank.service.concrete;

import com.azecoders.rrbank.dao.entity.UserEntity;
import com.azecoders.rrbank.dao.repository.UserRepository;
import com.azecoders.rrbank.exception.AccountFoundException;
import com.azecoders.rrbank.exception.UserFoundException;
import com.azecoders.rrbank.model.enums.UserRole;
import com.azecoders.rrbank.model.enums.UserStatus;
import com.azecoders.rrbank.model.requests.CreateLoginRequest;
import com.azecoders.rrbank.model.requests.CreateRefreshTokenRequest;
import com.azecoders.rrbank.model.response.LoginResponse;
import com.azecoders.rrbank.service.abstraction.AuthService;
import com.azecoders.rrbank.util.VerificationCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSendException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceHandler implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final MailServiceHandler mailServiceHandler;

    @Override
    public LoginResponse login(CreateLoginRequest loginRequest) {
        UserEntity user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new AccountFoundException("User not found", HttpStatus.BAD_REQUEST));

        if (user.getLoginAttempts() >= 3) {
            user.setUserStatus(UserStatus.BLOCKED);
            userRepository.save(user);
        }

        if (user.getUserStatus() == UserStatus.BLOCKED) {
            throw new AccountFoundException("Account is blocked. Contact admin.", HttpStatus.LOCKED);
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            user.setLoginAttempts(user.getLoginAttempts() + 1);
            userRepository.save(user);
            throw new AccountFoundException("Password incorrect", HttpStatus.BAD_REQUEST);
        }

        user.setUserStatus(UserStatus.ACTIVE);
        String accessToken = jwtService.generateAccessToken(user.getId(), user.getUserRole());
        String refreshToken = jwtService.generateRefreshToken(user.getId(), user.getUserRole());
        refreshTokenService.saveRefreshToken(user.getId(), refreshToken);

        return LoginResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .prefixs(String.valueOf(user.getPhonePrefix()))
                .isVerified(user.isVerified())
                .userStatus(user.getUserStatus())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }

    @Override
    public LoginResponse refreshToken(CreateRefreshTokenRequest refreshTokenRequest) {
        String id = jwtService.extractId(refreshTokenRequest.getRefreshToken());

        UserEntity user = userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new UserFoundException("User not found", HttpStatus.BAD_REQUEST));

        String savedRefreshToken = refreshTokenService.getRefreshToken(Long.valueOf(id));

        if (savedRefreshToken == null || !savedRefreshToken.equals(refreshTokenRequest.getRefreshToken())) {
            throw new RuntimeException("Refresh token invalid");
        }

        String newAccessToken = jwtService.generateAccessToken(Long.valueOf(id), user.getUserRole());
        String newRefreshToken = jwtService.generateRefreshToken(Long.valueOf(id), user.getUserRole());

        refreshTokenService.saveRefreshToken(Long.valueOf(id), newRefreshToken);

        return LoginResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();

    }

    @Override
    public void logout(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserFoundException("User not found by this email: " + email, HttpStatus.BAD_REQUEST));
        user.setUserStatus(UserStatus.LOGOUT);
        userRepository.save(user);
        refreshTokenService.deleteRefreshToken(email);
    }

    @Override
    public void resetPassword(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserFoundException("User not found by this email: " + email, HttpStatus.BAD_REQUEST));
        if (user.getUserStatus() == UserStatus.BLOCKED) {
            throw new AccountFoundException("Account is blocked. Contact admin.", HttpStatus.LOCKED);
        } else {
            String resetOtp = VerificationCodeGenerator.generateCode();
            user.setOtpCode(resetOtp);
            userRepository.save(user);
            mailServiceHandler.sendVerificationCode(user.getEmail(), resetOtp, user.getFullName());

        }
    }

    public String verifyReset(String verificationCode, String newPassword) {
        UserEntity user = userRepository.findByOtpCode(verificationCode)
                .orElseThrow(() -> new MailSendException("Valid or expired code"));
        if (passwordEncoder.matches(newPassword, user.getPassword())) {
            throw new IllegalArgumentException("New password cannot be the same as the old one");
        }
        String encodedPass = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPass);
        user.setOtpCode(null);
        userRepository.save(user);
        mailServiceHandler.sendPasswordResetMessage(user.getEmail(), user.getFullName());
        return "Password updated";
    }

}
