package com.azecoders.rrbank.model.requests;

import com.azecoders.rrbank.model.enums.AccountCurrency;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class CreateAccountRequest {
    @NotBlank(message = "Account currency must be selected")
    AccountCurrency accountType;
}
