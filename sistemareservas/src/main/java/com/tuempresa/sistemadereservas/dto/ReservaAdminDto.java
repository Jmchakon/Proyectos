package com.tuempresa.sistemadereservas.dto;

import com.tuempresa.sistemadereservas.entity.Pago;
import com.tuempresa.sistemadereservas.entity.Recurso;
import com.tuempresa.sistemadereservas.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ReservaAdminDto {
    private Integer idReserva;
    private Usuario usuario;
    private List<Recurso> recursos;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String estadoReservacion;
    private List<Pago> pagos;
    private double total;
}
