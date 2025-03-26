package com.webapiseplag.exceptions;

public class OperationNotAllowedException extends RuntimeException {

    private final String reason;
    private final String suggestedAction;

    // Construtor principal
    public OperationNotAllowedException(String message, String reason, String suggestedAction) {
        super(message);
        this.reason = reason;
        this.suggestedAction = suggestedAction;
    }

    // Construtor alternativo (com parâmetros diferentes)
    public OperationNotAllowedException(String resource, String operation, String reason, String suggestedAction) {
        this(String.format("Operação '%s' não permitida para %s", operation, resource),
                reason,
                suggestedAction);
    }

    // Construtor simplificado
    public OperationNotAllowedException(String resource, String operation) {
        this(resource,
                operation,
                "Restrição de regra de negócio",
                "Verifique as condições necessárias para esta operação");
    }

    public String getReason() {
        return reason;
    }

    public String getSuggestedAction() {
        return suggestedAction;
    }

    public String getFullMessage() {
        return String.format("%s. Razão: %s. Sugestão: %s",
                getMessage(), reason, suggestedAction);
    }
}