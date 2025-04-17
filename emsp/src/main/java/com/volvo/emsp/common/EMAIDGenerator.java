package com.volvo.emsp.common;

import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Pattern;

public class EMAIDGenerator {

    private static final String COUNTRY_CODES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHANUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Pattern EMAID_PATTERN = Pattern.compile(
        "^[A-Z]{2}-[A-Z0-9]{3}-[A-Z0-9]{9}-[A-Z0-9]$"
    );
    
    private static final Random random = new SecureRandom();

    /**
     * 生成随机EMAID
     * @param countryCode 2字母国家代码(ISO 3166-1 alpha-2)
     * @param providerCode 3字母数字提供商代码
     * @return 有效的EMAID字符串
     */
    public static String generate(String countryCode, String providerCode) {
        validateCountryCode(countryCode);
        validateProviderCode(providerCode);
        
        String uniquePart = generateRandomPart(9);
        String checkDigit = generateCheckDigit(countryCode, providerCode, uniquePart);
        
        return String.format("%s-%s-%s-%s", 
            countryCode.toUpperCase(), 
            providerCode.toUpperCase(), 
            uniquePart, 
            checkDigit);
    }

    /**
     * 验证EMAID格式是否有效
     * @param emaid 要验证的EMAID字符串
     * @return 是否有效
     */
    public static boolean isValid(String emaid) {
        if (emaid == null || emaid.length() != 18) {
            return false;
        }
        if (!EMAID_PATTERN.matcher(emaid).matches()) {
            return false;
        }
        
        String[] parts = emaid.split("-");
        if (parts.length != 4) return false;
        
        String checkDigit = generateCheckDigit(parts[0], parts[1], parts[2]);
        return parts[3].equals(checkDigit);
    }

    private static String generateRandomPart(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }
        return sb.toString();
    }

    private static String generateCheckDigit(String countryCode, String providerCode, String uniquePart) {
        String base = countryCode + providerCode + uniquePart;
        int sum = 0;
        
        // 简单校验和算法示例 (实际规范可能不同)
        for (int i = 0; i < base.length(); i++) {
            char c = base.charAt(i);
            int value = Character.isDigit(c) ? 
                Character.getNumericValue(c) : 
                (c - 'A' + 10);
            sum += value * (i % 2 == 0 ? 1 : 3); // 交替权重
        }
        
        int remainder = sum % 36;
        return String.valueOf(ALPHANUMERIC.charAt(remainder));
    }

    private static void validateCountryCode(String code) {
        if (code == null || code.length() != 2) {
            throw new IllegalArgumentException("Country code must be 2 letters");
        }
        for (char c : code.toCharArray()) {
            if (COUNTRY_CODES.indexOf(c) == -1) {
                throw new IllegalArgumentException("Invalid country code: " + code);
            }
        }
    }

    private static void validateProviderCode(String code) {
        if (code == null || code.length() != 3) {
            throw new IllegalArgumentException("Provider code must be 3 characters");
        }
        for (char c : code.toCharArray()) {
            if (ALPHANUMERIC.indexOf(c) == -1) {
                throw new IllegalArgumentException("Invalid provider code: " + code);
            }
        }
    }

    // 测试用例
    public static void main(String[] args) {
        // 生成示例EMAID
        String emaid = EMAIDGenerator.generate("DE", "B1M");
        System.out.println("Generated EMAID: " + emaid);
        System.out.println("Is valid? " + EMAIDGenerator.isValid(emaid));
        
        // 测试验证
        String testEMAID = "DE-B1M-8A9B7C6D5-E";
        System.out.println("\nTest EMAID: " + testEMAID);
        System.out.println("Is valid? " + EMAIDGenerator.isValid(testEMAID));
    }
}