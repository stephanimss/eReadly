package com.ereadly.exception;

public class BookNotAvailableException extends Exception {

    public BookNotAvailableException() {
        super("Stok buku tidak tersedia");
    }

    public BookNotAvailableException(String message) {
        super(message);
    }
}
