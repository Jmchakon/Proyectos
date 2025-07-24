package com.tuempresa.sistemadereservas.dto;

import com.tuempresa.sistemadereservas.entity.Reserva;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagoAdminDto {
    private Integer idPago;
    private String tipoRecurso;
    private List<Reserva> reservas;
    private double monto;
}
