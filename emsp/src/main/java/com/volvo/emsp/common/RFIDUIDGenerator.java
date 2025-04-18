package com.volvo.emsp.common;

import java.util.Random;

public class RFIDUIDGenerator {

    // 生成随机的厂商标识符（1 字节）
    private static String generateManufacturerID() {
        Random random = new Random();
        int manufacturerID = random.nextInt(256); // 0x00 到 0xFF
        return String.format("%02X", manufacturerID); // 转换为两位十六进制字符串
    }

    // 生成随机的序列号（3 字节）
    private static String generateSerialNumber() {
        Random random = new Random();
        StringBuilder serialNumber = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int byteValue = random.nextInt(256); // 0x00 到 0xFF
            serialNumber.append(String.format("%02X", byteValue));
        }
        return serialNumber.toString();
    }

    // 计算校验位（1 字节，简单 XOR 示例）
    private static String calculateChecksum(String uidPart) {
        int checksum = 0;
        for (int i = 0; i < uidPart.length(); i += 2) {
            String byteStr = uidPart.substring(i, i + 2);
            int byteValue = Integer.parseInt(byteStr, 16);
            checksum ^= byteValue; // 异或运算
        }
        return String.format("%02X", checksum); // 转换为两位十六进制字符串
    }

    // 生成完整的 UID
    public static String generateUID() {
        // 生成厂商标识符
        String manufacturerID = generateManufacturerID();

        // 生成序列号
        String serialNumber = generateSerialNumber();

        // 拼接前 6 字节（厂商标识符 + 序列号）
        String uidPart = manufacturerID + serialNumber;

        // 计算校验位
        String checksum = calculateChecksum(uidPart);

        // 返回完整的 UID
        return uidPart + checksum;
    }

    public static void main(String[] args) {
        // 测试生成 UID
        String uid = generateUID();
        System.out.println("Generated RFID UID: " + uid);
    }
}