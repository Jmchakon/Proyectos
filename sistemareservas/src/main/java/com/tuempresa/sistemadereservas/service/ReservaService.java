package com.tuempresa.sistemadereservas.service;

import com.tuempresa.sistemadereservas.entity.Reserva;

import java.util.List;

public interface ReservaService {
    Reserva crearReserva(Reserva reserva);
    Reserva obtenerReservaPorId(Integer id);
    List<Reserva> listarReservas();
    Reserva actualizarReserva(Reserva reserva);
    void eliminarReserva(Integer id);
    double calcularTotalReserva(Integer id);
    public double obtenerTotalReserva(Integer idReserva);
}
