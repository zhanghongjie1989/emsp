package com.volvo.emsp.common;

import java.util.regex.Pattern;

public class EMAIDValidator {

    private static final Pattern EMAID_PATTERN = Pattern.compile(
        "^[A-Z]{2}-[A-Z0-9]{3}-[A-Z0-9]{9}-[A-Z0-9]$"
    );

    public static boolean isValid(String emaid) {
        if (emaid == null || emaid.length() != 17) {
            return false;
        }
        return EMAID_PATTERN.matcher(emaid).matches();
    }

    public static void validate(String emaid) {
        if (!isValid(emaid)) {
            throw new RuntimeException("Invalid EMAID format: " + emaid);
        }
    }
}