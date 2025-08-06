package com.azecoders.rrbank.model.requests;

import com.azecoders.rrbank.model.enums.PhonePrefixs;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class CreateRegisterRequest {
    @NotBlank(message = "Full name cannot be empty")
    String fullName;
    @NotBlank(message = "Prefixs must be selected")
    PhonePrefixs prefixs;
    @NotBlank(message = "Phone number must be entered")
    String phoneNumber;
    @NotBlank(message = "Email cannot be empty")
    @Email(regexp = "^[\\w.-]+@[\\w.-]+\\.(com|ru)$", message = "The email must also have the @ symbol and end with .com or .ru.")
    String email;
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6,max = 255)
    String password;

}
