package com.ereadly.exception;

public class BookNotAvailableException extends Exception {

    public BookNotAvailableException() {
        super("Buku tidak tersedia untuk dipinjam");
    }

    public BookNotAvailableException(String message) {
        super(message);
    }
}
