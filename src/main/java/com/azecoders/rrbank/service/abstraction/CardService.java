package com.azecoders.rrbank.service.abstraction;

import com.azecoders.rrbank.model.enums.CardType;
import com.azecoders.rrbank.model.requests.CreateAccountRequest;
import com.azecoders.rrbank.model.requests.CreateCardRequest;
import com.azecoders.rrbank.model.response.CardResponse;

public interface CardService {
    CardResponse orderCard(CreateCardRequest cardRequest, String email, CardType cardType);
}
