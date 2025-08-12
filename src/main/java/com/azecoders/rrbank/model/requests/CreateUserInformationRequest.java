package com.azecoders.rrbank.model.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class CreateUserInformationRequest {
    @NotBlank(message = "You must be enter birth date")
    LocalDate birthDate;
    @NotBlank(message = "You must be enter address")
    String adress;
    @NotBlank(message = "You must be enter national Id")
    @Size(min = 7, max = 7)
    String nationalId;
}
