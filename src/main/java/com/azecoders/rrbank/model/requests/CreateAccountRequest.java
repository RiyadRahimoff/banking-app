package com.azecoders.rrbank.model.requests;

import com.azecoders.rrbank.model.enums.AccountCurrency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class CreateAccountRequest {
    @NotNull(message = "Balance cannot be null!")
    BigDecimal balance;

    @NotBlank(message = "Account currency must be selected")
    AccountCurrency accountType;

    @NotEmpty(message = "User id cannot be empty!")
    Long userId;

}
