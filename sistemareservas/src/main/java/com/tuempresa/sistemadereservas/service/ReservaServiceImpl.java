package com.tuempresa.sistemadereservas.service;

import com.tuempresa.sistemadereservas.entity.Recurso;
import com.tuempresa.sistemadereservas.entity.Reserva;
import com.tuempresa.sistemadereservas.exception.ReservaNoDisponibleException;
import com.tuempresa.sistemadereservas.exception.ResourceNotFoundException;
import com.tuempresa.sistemadereservas.repository.ReservaRepository;
import com.tuempresa.sistemadereservas.util.DateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {
    @Autowired
    ReservaRepository reservaRepository;


    @Override
    public Reserva crearReserva(Reserva reserva) {
        if (!DateValidator.isFechaInicioAntesQueFin(reserva.getFechaInicio(), reserva.getFechaFin())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la de fin.");
        }

        if (!DateValidator.isFechaInicioNoPasada(reserva.getFechaInicio())) {
            throw new IllegalArgumentException("No se puede hacer una reserva en el pasado.");
        }

        if (!DateValidator.isDuracionValida(reserva.getFechaInicio(), reserva.getFechaFin(), 7)) {
            throw new IllegalArgumentException("La reserva no puede durar más de 7 días.");
        }



        Optional<Reserva> reservaExistente = reservaRepository.findByUsuarioAndFechaReserva(reserva.getUsuario(),reserva.getFechaInicio());
        if(reservaExistente.isPresent()){
            throw new ReservaNoDisponibleException("Ya existe una reserva para el usuario " + reserva.getUsuario().getEmail() +
                    " en la fecha " + reserva.getFechaInicio());
        }
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva obtenerReservaPorId(Integer id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        if(reserva.isEmpty()){
            throw new ResourceNotFoundException("Reserva con ID " + id + " no encontrada.");
        }
        return reserva.get();
    }

    @Override
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva actualizarReserva(Reserva reserva) {
        if (reservaRepository.existsById(reserva.getIdReserva())) {
          return   reservaRepository.save(reserva);
        }else{
            throw new ResourceNotFoundException("Reserva con ID " + reserva.getIdReserva() + " no encontrada.");
        }
    }

    @Override
    public void eliminarReserva(Integer id) {
        if(reservaRepository.existsById(id)){
        reservaRepository.deleteById(id);
        }else {
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

