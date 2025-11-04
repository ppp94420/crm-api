package com.crm.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @Author: DELL
 * @Date: 2025/11/2
 * @Version: 1.0
 */
public class NumberUtils {
    public static String generateContractNumber() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timePart = LocalDateTime.now().format(formatter);
        String randomPart = getRandomSting(4);
        return "HT" + timePart + randomPart;
    }
    public static String getRandomSting(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
