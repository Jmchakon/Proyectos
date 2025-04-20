package com.tuempresa.sistemadereservas.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {

    private String message;   // El mensaje de error
    private String details;   // Detalles adicionales sobre el error (como la URI)

    // Constructor
    public ErrorDetails(String message, String details) {
        this.message = message;
        this.details = details;
    }
}
