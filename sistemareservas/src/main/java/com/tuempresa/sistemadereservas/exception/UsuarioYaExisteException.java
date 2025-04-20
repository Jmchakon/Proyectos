package com.tuempresa.sistemadereservas.exception;

public class UsuarioYaExisteException extends RuntimeException {
    public UsuarioYaExisteException(String message) {
        super(message);
    }
}
