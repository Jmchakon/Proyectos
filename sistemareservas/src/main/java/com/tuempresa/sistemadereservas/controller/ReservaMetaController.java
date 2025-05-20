package com.tuempresa.sistemadereservas.controller;
import com.tuempresa.sistemadereservas.util.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reservas/meta")
public class ReservaMetaController {

    @GetMapping("/estados")
    public List<String> getEstadosReserva() {
        return List.of(
                Constants.RESERVA_ESTADO_PENDIENTE,
                Constants.RESERVA_ESTADO_CONFIRMADA,
                Constants.RESERVA_ESTADO_CANCELADA
        );
    }

    @GetMapping("/tipos-recurso")
    public List<String> getTiposRecurso() {
        return List.of(
                Constants.TIPO_RECURSO_HABITACION_SIMPLE,
                Constants.TIPO_RECURSO_HABITACION_DOBLE,
                Constants.TIPO_RECURSO_MESA_SALA,
                Constants.TIPO_RECURSO_MESA_TERRAZA,
                Constants.TIPO_RECURSO_SALA_GRANDE,
                Constants.TIPO_RECURSO_SALA_PEQUENA
        );
    }
}
