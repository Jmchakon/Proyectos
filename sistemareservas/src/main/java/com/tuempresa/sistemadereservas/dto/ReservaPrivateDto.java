package com.tuempresa.sistemadereservas.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ReservaPrivateDto {
    private List<RecursoPrivateDto> recursos;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String estadoReservacion;
    private List<PagoPrivateDto> pago;
    private double total;

}
