package com.azecoders.rrbank.model.response;

import com.azecoders.rrbank.model.enums.AccountCurrency;
import com.azecoders.rrbank.model.enums.OrderStatus;
import com.azecoders.rrbank.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class AccountResponse {
    Long id;
    Long userId;
    String fullName;
    String email;
    String phoneNumber;
    String prefixs;
    boolean isVerified;
    UserStatus userStatus;
    OrderStatus orderStatus;
    String accountNumber;
    BigDecimal balance;
    AccountCurrency accountType;
}
