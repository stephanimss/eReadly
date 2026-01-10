package com.ereadly.util;

public class CredentialUtil {

    private CredentialUtil() {
        // prevent instantiation
    }

    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isLoginInputValid(String email, String password) {
        return !isEmpty(email) && !isEmpty(password);
    }
}
