package com.azecoders.rrbank.service.abstraction;

import com.azecoders.rrbank.model.requests.CreateUserInformationRequest;

public interface UserService {
    void updateInfo(CreateUserInformationRequest createUserInformationRequest,String email);
    String orderAccount();
}
