package com.ereadly.exception;

public class AuthenticationException extends Exception {

    public AuthenticationException() {
        super("Email atau password salah");
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
