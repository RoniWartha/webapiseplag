package com.webapiseplag.dtos;

public record ExceptionDTO(
        String message,
        String statusCode,
        String timestamp
) {
    public ExceptionDTO(String message, String statusCode) {
        this(message, statusCode, java.time.LocalDateTime.now().toString());
    }
}
