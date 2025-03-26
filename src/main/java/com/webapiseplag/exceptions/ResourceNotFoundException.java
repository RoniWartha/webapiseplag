package com.webapiseplag.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, Long id) {
        super(id != null ?
                String.format("%s com ID %d não encontrado", resource, id) :
                String.format("%s não encontrado", resource));
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
