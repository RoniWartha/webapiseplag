package com.webapiseplag.exceptions;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String resource, String details) {
        super("Já existe um(a) " + resource + " com " + details);
    }

    public DuplicateResourceException(String message) {
        super(message);
    }
}
