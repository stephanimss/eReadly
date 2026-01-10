package com.ereadly.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    private PasswordUtil() {}

    public static boolean isValidPassword(String password, String role) {
        if (password == null || password.length() < 6) return false;

        if ("ADMIN".equalsIgnoreCase(role)) {
            return password.matches(".*[A-Z].*") && password.matches(".*[0-9].*");
        }
        return true;
    }

    public static String hashPassword(String password) {
        if (password == null) return null;
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritma Hashing tidak ditemukan", e);
        }
    }

    public static boolean checkPassword(String rawPassword, String hashedPassword) {
        if (rawPassword == null || hashedPassword == null) return false;
        
        String inputHash = hashPassword(rawPassword);
        return inputHash != null && inputHash.equals(hashedPassword);
    }
}