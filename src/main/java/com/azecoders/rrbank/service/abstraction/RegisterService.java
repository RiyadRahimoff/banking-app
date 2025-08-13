package com.azecoders.rrbank.service.abstraction;

import com.azecoders.rrbank.model.requests.CreateRegisterRequest;
import com.azecoders.rrbank.model.response.RegisterResponse;

public interface RegisterService {
    RegisterResponse registerUser(CreateRegisterRequest registerRequest);

    String verifyUser(String verificationCode);
}
