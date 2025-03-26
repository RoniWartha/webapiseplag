package com.webapiseplag.exceptions;

import com.webapiseplag.dtos.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionDTO(
                        ex.getMessage(),
                        "404",
                        request.getRequestURI()
                ));
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ExceptionDTO> handleDuplicate(
            DuplicateResourceException ex,
            HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ExceptionDTO(
                        ex.getMessage(),
                        "409",
                        request.getRequestURI()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleGeneral(
            Exception ex,
            HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionDTO(
                        "Erro interno no servidor",
                        "500",
                        request.getRequestURI()
                ));
    }

    @ExceptionHandler(OperationNotAllowedException.class)
    public ResponseEntity<ExceptionDTO> handleOperationNotAllowed(
            OperationNotAllowedException ex,
            HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ExceptionDTO(
                        ex.getFullMessage(),
                        "403",
                        request.getRequestURI()
                ));
    }
}
