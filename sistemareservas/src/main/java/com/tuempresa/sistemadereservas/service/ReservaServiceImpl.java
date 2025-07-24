package com.tuempresa.sistemadereservas.service;

import com.tuempresa.sistemadereservas.entity.Pago;
import com.tuempresa.sistemadereservas.entity.Recurso;
import com.tuempresa.sistemadereservas.entity.Reserva;
import com.tuempresa.sistemadereservas.entity.Usuario;
import com.tuempresa.sistemadereservas.exception.ReservaNoDisponibleException;
import com.tuempresa.sistemadereservas.exception.ResourceNotFoundException;
import com.tuempresa.sistemadereservas.repository.RecursoRepository;
import com.tuempresa.sistemadereservas.repository.ReservaRepository;
import com.tuempresa.sistemadereservas.util.DateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RecursoRepository recursoRepository;

    public Reserva crearReserva(Reserva reserva) {
        // Validaciones de fechas
        if (!DateValidator.isFechaInicioAntesQueFin(reserva.getFechaInicio(), reserva.getFechaFin())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la de fin.");
        }

        if (!DateValidator.isFechaInicioNoPasada(reserva.getFechaInicio())) {
            throw new IllegalArgumentException("No se puede hacer una reserva en el pasado.");
        }

        if (!DateValidator.isDuracionValida(reserva.getFechaInicio(), reserva.getFechaFin(), 7)) {
            throw new IllegalArgumentException("La reserva no puede durar más de 7 días.");
        }

        // Validación de usuario
        if (reserva.getUsuario() == null || reserva.getUsuario().getIdUsuario() == null) {
            throw new IllegalArgumentException("Debe proporcionar un usuario válido para la reserva.");
        }

        // Obtener usuario completo
        Usuario usuario = usuarioService.obtenerUsuarioPorId(reserva.getUsuario().getIdUsuario());
        reserva.setUsuario(usuario);

        // Validar si ya existe una reserva del mismo usuario en la misma fecha
        Optional<Reserva> reservaExistente = reservaRepository.findByUsuarioAndFechaInicio(usuario, reserva.getFechaInicio());
        if (reservaExistente.isPresent()) {
            throw new ReservaNoDisponibleException("Ya existe una reserva para el usuario " + usuario.getEmail() +
                    " en la fecha " + reserva.getFechaInicio());
        }

        // Cargar recursos completos desde BD para asegurar que existen
        if (reserva.getRecursos() != null && !reserva.getRecursos().isEmpty()) {
            List<Recurso> recursosValidos = new ArrayList<>();
            for (Recurso recursoInput : reserva.getRecursos()) {
                Recurso recursoBD = recursoRepository.findById(recursoInput.getIdRecurso())
                        .orElseThrow(() -> new IllegalArgumentException("Recurso con ID " + recursoInput.getIdRecurso() + " no encontrado"));
                recursosValidos.add(recursoBD);
            }
            reserva.setRecursos(recursosValidos);
        }

        // Asignar usuario y reserva a cada pago
        if (reserva.getPago() != null) {
            for (Pago pago : reserva.getPago()) {
                pago.setUsuario(usuario);
                pago.setReserva(reserva);
            }
        }

        // Guardar la reserva (guardará pagos en cascada)
        return reservaRepository.save(reserva);
    }



    @Override
    public Reserva obtenerReservaPorId(Integer id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva con ID " + id + " no encontrada."));
    }

    @Override
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva actualizarReserva(Reserva reserva) {
        if (reservaRepository.existsById(reserva.getIdReserva())) {
            return reservaRepository.save(reserva);
        } else {
            throw new ResourceNotFoundException("Reserva con ID " + reserva.getIdReserva() + " no encontrada.");
        }
    }

    @Override
    public void eliminarReserva(Integer id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Reserva con ID " + id + " no encontrada.");
        }
    }

    public double calcularTotalReserva(Integer idReserva) {
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva con ID " + idReserva + " no encontrada."));

        double total = 0.0;
        for (Recurso recurso : reserva.getRecursos()) {
            total += recurso.getPrecio();
        }

        return total;
    }

    @Override
    public double obtenerTotalReserva(Integer idReserva) {
        return calcularTotalReserva(idReserva);
    }
}
