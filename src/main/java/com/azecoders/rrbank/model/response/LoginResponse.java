package com.azecoders.rrbank.model.response;

import com.azecoders.rrbank.model.enums.UserStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class LoginResponse {
    Long id;
    String fullName;
    String email;
    String phoneNumber;
    String prefixs;
    boolean isVerified;
    UserStatus userStatus;
    String accessToken;
    String refreshToken;



}
