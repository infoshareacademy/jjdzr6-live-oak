package com.infoshareacademy.utils;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;

public class PasswordGenerator {
    public static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String LOWER = UPPER.toLowerCase(Locale.ROOT);

    public static final String DIGITS = "0123456789";

    public static final String ALPHA_NUM = UPPER + LOWER + DIGITS;

    public PasswordGenerator() {
        throw new IllegalStateException("Utility class");
    }

    public static String generateRandomPassword(int length) {
        Random random = new SecureRandom();
        if (length < 1) throw new IllegalArgumentException();

        char[] symbols = ALPHA_NUM.toCharArray();
        char[] buf = new char[length];

        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = symbols[random.nextInt(symbols.length)];
        }

        return new String(buf);
    }
}
