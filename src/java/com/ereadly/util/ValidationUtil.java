package com.ereadly.util;

public class ValidationUtil {

    private ValidationUtil() {}

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static String detectRoleByEmail(String email) {
        if (email == null) return "member";

        if (email.endsWith("@admin.com")) {
            return "admin";
        }
        return "member";
    }
}
