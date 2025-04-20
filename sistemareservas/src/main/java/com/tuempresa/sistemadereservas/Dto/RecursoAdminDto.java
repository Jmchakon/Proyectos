package com.tuempresa.sistemadereservas.Dto;

import com.tuempresa.sistemadereservas.entity.Reserva;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecursoAdminDto {
    private Integer idRecurso;
    private String tipoRecurso;
    private List<Reserva> reserva;
    private double precio;
    private String nombre;
}
