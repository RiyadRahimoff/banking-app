package com.azecoders.rrbank.mapper;

import com.azecoders.rrbank.dao.entity.UserEntity;
import com.azecoders.rrbank.model.enums.UserRole;
import com.azecoders.rrbank.model.enums.UserStatus;
import com.azecoders.rrbank.model.requests.CreateRegisterRequest;
import com.azecoders.rrbank.model.response.LoginResponse;
import com.azecoders.rrbank.model.response.RegisterResponse;

public class UserMapper {
    public static UserEntity toEntity(CreateRegisterRequest registerRequest, String encodedPassword, String otpCode) {
        return UserEntity.builder()
                .fullName(registerRequest.getFullName())
                .phonePrefixs(registerRequest.getPrefixs())
                .phoneNumber(registerRequest.getPhoneNumber())
                .email(registerRequest.getEmail())
                .password(encodedPassword)
                .otpCode(otpCode)
                .userRole(UserRole.USER)
                .isVerified(false)
                .userStatus(UserStatus.INACTIVE)
                .build();
    }

    public static RegisterResponse toResponse(UserEntity user){
        return RegisterResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .build();
    }


}
