package com.example.finapp.domain.exceptions;

public class DatabaseException extends Exception {

    public DatabaseException(String message) {
        super("Falha no banco!" + message);
    }

    public DatabaseException(String message, Throwable cause) {
        super("Falha no banco!" + message, cause);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }
}
