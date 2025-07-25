package com.tuempresa.sistemadereservas.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagoPrivateDto {
    private double monto;
    private List<ReservaPrivateDto> reservas;
}
