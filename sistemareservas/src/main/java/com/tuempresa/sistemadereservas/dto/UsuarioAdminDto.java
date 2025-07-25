package com.tuempresa.sistemadereservas.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import com.tuempresa.sistemadereservas.entity.Reserva;
@Getter
@Setter
public class UsuarioAdminDto {
    private Integer idUsuario;
    private String nombreCompleto;
    private Integer numTelefono;
    private List<Reserva> reservas;
    private String email;
}
