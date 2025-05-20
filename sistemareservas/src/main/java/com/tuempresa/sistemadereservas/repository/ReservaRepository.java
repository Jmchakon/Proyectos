package com.tuempresa.sistemadereservas.repository;

import com.tuempresa.sistemadereservas.entity.Reserva;
import com.tuempresa.sistemadereservas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva,Integer> {
        Optional<Reserva> findByUsuarioAndFechaReserva(Usuario usuario, LocalDateTime fechaInicio);

}
