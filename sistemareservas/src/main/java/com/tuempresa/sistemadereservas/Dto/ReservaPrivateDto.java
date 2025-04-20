package com.tuempresa.sistemadereservas.Dto;

import com.tuempresa.sistemadereservas.entity.Pago;
import com.tuempresa.sistemadereservas.entity.Usuario;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ReservaPrivateDto {
    private List<RecursoPrivateDto> recursos;
    private LocalDateTime fechaReservacion;
    private String estadoReservacion;
    private List<PagoPrivateDto> pago;
    private double total;

}
