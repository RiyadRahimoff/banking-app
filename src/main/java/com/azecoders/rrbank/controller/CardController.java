package com.azecoders.rrbank.controller;

import com.azecoders.rrbank.model.enums.CardType;
import com.azecoders.rrbank.model.requests.CreateCardRequest;
import com.azecoders.rrbank.model.response.CardResponse;
import com.azecoders.rrbank.service.concrete.CardServiceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardServiceHandler cardServiceHandler;


    @PostMapping("/order")
    public CardResponse orderCard(@RequestBody CreateCardRequest cardRequest, String email, CardType cardType) {
        return cardServiceHandler.orderCard(cardRequest,email,cardType);
    }
}
