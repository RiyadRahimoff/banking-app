package com.azecoders.rrbank.service.concrete;

import com.azecoders.rrbank.dao.entity.UserEntity;
import com.azecoders.rrbank.dao.repository.UserRepository;
import com.azecoders.rrbank.model.requests.CreateLoginRequest;
import com.azecoders.rrbank.model.requests.CreateRefreshTokenRequest;
import com.azecoders.rrbank.model.response.LoginResponse;
import com.azecoders.rrbank.service.abstraction.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceHandler implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @Override
    public LoginResponse login(CreateLoginRequest loginRequest) {
        UserEntity user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Password incorrect");
        }

        String accessToken = jwtService.generateAccessToken(user.getId());
        String refreshToken = jwtService.generateRefreshToken(user.getId());
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

        String savedRefreshToken = refreshTokenService.getRefreshToken(Long.valueOf(id));

        if (savedRefreshToken == null || !savedRefreshToken.equals(refreshTokenRequest.getRefreshToken())) {
            throw new RuntimeException("Refresh token invalid");
        }

        String newAccessToken = jwtService.generateAccessToken(Long.valueOf(id));
        String newRefreshToken = jwtService.generateRefreshToken(Long.valueOf(id));

        refreshTokenService.saveRefreshToken(Long.valueOf(id), newRefreshToken);

        return LoginResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();

    }

    @Override
    public void logout(String email) {
        refreshTokenService.deleteRefreshToken(email);
    }
}
