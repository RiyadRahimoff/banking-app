package com.azecoders.rrbank.model.response;

import com.azecoders.rrbank.model.enums.CardStatus;
import com.azecoders.rrbank.model.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class CardResponse {
    Long id;

    CardStatus cardStatus;

    String cardNumber;

    CardType cardType;

    String expiryDate;

    String accountNumber;


}
