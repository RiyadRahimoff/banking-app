package com.azecoders.rrbank.util;

import java.security.SecureRandom;
import java.util.Random;

public class AccountNumberGenerator {
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            accountNumber.append(RANDOM.nextInt(10));
        }

        return accountNumber.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(generateAccountNumber());
        }
    }

}
