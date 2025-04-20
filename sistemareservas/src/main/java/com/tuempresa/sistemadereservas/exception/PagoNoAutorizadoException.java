package com.tuempresa.sistemadereservas.exception;

public class PagoNoAutorizadoException extends RuntimeException {
    public PagoNoAutorizadoException(String message) {
        super(message);
    }
}
