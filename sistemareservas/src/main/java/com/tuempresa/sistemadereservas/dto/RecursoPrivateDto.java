package com.tuempresa.sistemadereservas.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecursoPrivateDto {
    private String tipoRecurso;
    private List<ReservaPrivateDto> reserva;
    private double precio;
    private String nombre;
}
