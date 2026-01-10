package com.ereadly.exception;

public class BookNotFoundException extends Exception {

    public BookNotFoundException() {
        super("Buku tidak ditemukan");
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
