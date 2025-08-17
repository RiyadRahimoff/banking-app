package com.azecoders.rrbank.util;

import java.util.Random;

public class CardNumberGenerator {
    private static final Random RANDOM = new Random();

    public static String generateCode() {
        String prefix = "77071010";
        StringBuilder sb = new StringBuilder(prefix);

        for (int i = 0; i < 8; i++) {
            sb.append(RANDOM.nextInt(10));
        }

        return sb.toString();
    }
}
