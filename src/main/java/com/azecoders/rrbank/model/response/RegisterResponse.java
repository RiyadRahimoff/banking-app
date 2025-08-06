package com.azecoders.rrbank.model.response;

import com.azecoders.rrbank.model.enums.UserStatus;
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
public class RegisterResponse {
    Long id;
    String fullName;
    String email;
    String phoneNumber;
    String prefixs;
    LocalDate birthDate;
    String address;
    String nationalID;
    boolean isVerified;
    UserStatus userStatus;
}
