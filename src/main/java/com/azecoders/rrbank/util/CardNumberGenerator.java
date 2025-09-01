package com.azecoders.rrbank.util;

import java.util.Random;

public class CardNumberGenerator {
    private static final Random RANDOM = new Random();

    public static String generateMasterCard() {

        String[] bins = {"51", "52", "53", "54", "55"};
        String bin = bins[RANDOM.nextInt(bins.length)];

        StringBuilder cardNumber = new StringBuilder(bin);

        while (cardNumber.length() < 15) {
            cardNumber.append(RANDOM.nextInt(10));
        }


        int checkDigit = getLuhnCheckDigit(cardNumber.toString());
        cardNumber.append(checkDigit);

        return cardNumber.toString();
    }

    private static int getLuhnCheckDigit(String number) {
        int sum = 0;
        boolean alternate = true;

        for (int i = number.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(number.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (10 - (sum % 10)) % 10;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            System.out.println(generateMasterCard());
        }
    }
}