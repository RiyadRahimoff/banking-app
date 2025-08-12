package com.azecoders.rrbank.service.concrete;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE)
public class RefreshTokenService {
    final RedisTemplate<String, String> redisTemplate;
    @Value("${jwt.refresh-expiration}")
    long refreshExpiration;

    public void saveRefreshToken(Long id,String refreshToken){
        redisTemplate.opsForValue().set(String.valueOf(id),refreshToken);
    }

    public String getRefreshToken(Long id){
        return redisTemplate.opsForValue().get(String.valueOf(id));
    }

    public void deleteRefreshToken(Long id) {
        redisTemplate.delete(String.valueOf(id));
    }

}
