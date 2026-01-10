package com.ereadly.exception;

public class AuthorizationException extends Exception {

    public AuthorizationException() {
        super("Anda tidak memiliki akses ke halaman ini");
    }

    public AuthorizationException(String message) {
        super(message);
    }
}
