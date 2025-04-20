package com.tuempresa.sistemadereservas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Manejo de la excepción ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        // Creamos el objeto ErrorDetails
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
        // Retornamos una respuesta HTTP con el código de estado 404 (No encontrado)
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // Manejo de la excepción ReservaNoDisponibleException
    @ExceptionHandler(ReservaNoDisponibleException.class)
    public ResponseEntity<?> handleReservaNoDisponibleException(ReservaNoDisponibleException ex, WebRequest request) {
        // Creamos el objeto ErrorDetails
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
        // Retornamos una respuesta HTTP con el código de estado 409 (Conflicto)
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    // Manejo de la excepción PagoNoAutorizadoException
    @ExceptionHandler(PagoNoAutorizadoException.class)
    public ResponseEntity<?> handlePagoNoAutorizadoException(PagoNoAutorizadoException ex, WebRequest request) {
        // Creamos el objeto ErrorDetails
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
        // Retornamos una respuesta HTTP con el código de estado 403 (Prohibido)
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    // Manejo de cualquier otra excepción no manejada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        // Creamos el objeto ErrorDetails
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
        // Retornamos una respuesta HTTP con el código de estado 500 (Error interno del servidor)
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public ResponseEntity<?> hadleUsuarioYaExistente(UsuarioYaExisteException ex, WebRequest  request){
        ErrorDetails errorDetails = new ErrorDetails((ex.getMessage()), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

}
