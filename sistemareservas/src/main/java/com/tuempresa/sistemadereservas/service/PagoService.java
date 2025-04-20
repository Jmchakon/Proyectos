package com.tuempresa.sistemadereservas.service;

import com.tuempresa.sistemadereservas.entity.Pago;

import java.util.List;

public interface PagoService {
    Pago crearPago(Pago pago);
    Pago obtenerPagoPorId(Integer id);
    List<Pago> listarPagos();
    Pago actualizarPago(Pago pago);
    void eliminarPago(Integer pago);
}
