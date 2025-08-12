package com.azecoders.rrbank.service.abstraction;

import com.azecoders.rrbank.model.requests.CreateLoginRequest;
import com.azecoders.rrbank.model.requests.CreateRefreshTokenRequest;
import com.azecoders.rrbank.model.response.LoginResponse;

public interface AuthService {
    LoginResponse login(CreateLoginRequest loginRequest);

    LoginResponse refreshToken(CreateRefreshTokenRequest refreshTokenRequest);

    void logout(String email);
}
