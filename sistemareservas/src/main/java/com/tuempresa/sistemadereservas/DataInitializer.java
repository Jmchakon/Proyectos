package com.tuempresa.sistemadereservas;

import com.tuempresa.sistemadereservas.entity.*;
import com.tuempresa.sistemadereservas.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final ReservaRepository reservaRepository;
    private final RecursoRepository recursoRepository;
    private final PagoRepository pagoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {

            // 1. Crear usuario admin
            Usuario admin = new Usuario();
            admin.setNombreCompleto("Administrador");
            admin.setNumTelefono(123456789);
            admin.setEmail("admin@admin.com");
            admin.setRole(Role.ADMIN);
            admin.setPassword("{noop}admin123"); // Sin encriptar para pruebas
            usuarioRepository.save(admin);

            // 2. Crear recurso
            Recurso recurso = new Recurso();
            recurso.setNombre("Sala de reuniones");
            recurso.setTipoDeRecurso(Recurso.TipoRecurso.valueOf("Sala"));

            recurso.setPrecio(100.0);
            recursoRepository.save(recurso);

            // 3. Crear reserva (con fechas ahora y +1 hora)
            Reserva reserva = new Reserva();
            reserva.setUsuario(admin);
            reserva.setRecursos(Collections.singletonList(recurso));
            reserva.setFechaInicio(LocalDateTime.now());
            reserva.setFechaFin(LocalDateTime.now().plusHours(1));
            reserva.setEstadoReservacion("CONFIRMADA");
            reservaRepository.save(reserva);

            // 4. Crear pago
            Pago pago = new Pago();
            pago.setReserva(reserva);
            pago.setUsuario(admin);
            pago.setMonto(100.0);
            pago.setFechaPago(LocalDateTime.now());
            pago.setMetodoPago("Tarjeta");
            pago.setEstadoPago("PAGADO");
            pago.setReferenciaPago("REF123456");
            pagoRepository.save(pago);

            System.out.println("Datos iniciales creados!");
        } else {
            System.out.println("Usuarios ya existen, no se crean datos iniciales.");
        }
    }
}
