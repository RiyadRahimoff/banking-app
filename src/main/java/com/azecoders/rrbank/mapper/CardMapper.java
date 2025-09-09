package com.azecoders.rrbank.mapper;

import com.azecoders.rrbank.dao.entity.CardEntity;
import com.azecoders.rrbank.model.requests.CreateCardRequest;

public class CardMapper {
    public static CardEntity toEntity(CreateCardRequest cardRequest){
        return CardEntity.builder()
                .build();
    }
}
