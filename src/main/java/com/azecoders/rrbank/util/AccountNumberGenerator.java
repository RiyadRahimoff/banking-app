package com.azecoders.rrbank.util;

import java.security.SecureRandom;

public class AccountNumberGenerator {
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateCode() {
        long code = 100000000000L + (Math.abs(RANDOM.nextLong()) % 900000000000L);
        return String.valueOf(code);
    }

}
