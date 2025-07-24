package com.tuempresa.sistemadereservas.service;

import com.tuempresa.sistemadereservas.entity.Pago;
import com.tuempresa.sistemadereservas.entity.Reserva;
import com.tuempresa.sistemadereservas.entity.Usuario;
import com.tuempresa.sistemadereservas.exception.PagoYaRealizadoException;
import com.tuempresa.sistemadereservas.exception.ResourceNotFoundException;
import com.tuempresa.sistemadereservas.repository.PagoRepository;
import com.tuempresa.sistemadereservas.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoServiceImpl implements PagoService {
    @Autowired
    PagoRepository pagoRepository;
    @Autowired
    ReservaService reservaService;
    @Autowired
    UsuarioService usuarioService;

    @Override
    public Pago crearPago(Pago pago) {
        // Validar que venga reserva y usuario con id
        if (pago.getReserva() == null || pago.getReserva().getIdReserva() == null) {
            throw new IllegalArgumentException("Debe proporcionar una reserva válida.");
        }
        if (pago.getUsuario() == null || pago.getUsuario().getIdUsuario() == null) {
            throw new IllegalArgumentException("Debe proporcionar un usuario válido.");
        }

        // Obtener reserva completa desde BD
        Reserva reserva = reservaService.obtenerReservaPorId(pago.getReserva().getIdReserva());
        pago.setReserva(reserva);

        // Obtener usuario completo desde BD
        Usuario usuario = usuarioService.obtenerUsuarioPorId(pago.getUsuario().getIdUsuario());
        pago.setUsuario(usuario);

        // Validar si ya existe pago con la misma referencia para esta reserva
        Optional<Pago> pagoExistente = pagoRepository.findByReferenciaPagoAndReserva_IdReserva(pago.getReferenciaPago(), reserva.getIdReserva());
        if (pagoExistente.isPresent()) {
            throw new PagoYaRealizadoException("Ya existe un pago con esa referencia para esta reserva.");
        }

        return pagoRepository.save(pago);
    }

    @Override
    public Pago obtenerPagoPorId(Integer id) {
     Optional<Pago> pago = pagoRepository.findById(id);
     if(pago.isEmpty()){
         throw new ResourceNotFoundException("Pago con ID " + id + " no encontrado.");

     }
     return  pago.get();
    }

    @Override
    public List<Pago> listarPagos() {
        return pagoRepository.findAll();
    }



    @Override
    public Pago actualizarPago(Integer id, Pago pagoActualizado) {
        Pago pagoExistente = pagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pago " + id + " no encontrado."));

        // Actualizamos solo los campos editables
        pagoExistente.setMonto(pagoActualizado.getMonto());
        pagoExistente.setFechaPago(pagoActualizado.getFechaPago());
        pagoExistente.setMetodoPago(pagoActualizado.getMetodoPago());
        pagoExistente.setEstadoPago(pagoActualizado.getEstadoPago());
        pagoExistente.setReferenciaPago(pagoActualizado.getReferenciaPago());

        // Actualizamos usuario si está presente
        if (pagoActualizado.getUsuario() != null && pagoActualizado.getUsuario().getIdUsuario() != null) {
            pagoExistente.setUsuario(pagoActualizado.getUsuario());
        }

        // Actualizamos reserva si está presente
        if (pagoActualizado.getReserva() != null && pagoActualizado.getReserva().getIdReserva() != null) {
            pagoExistente.setReserva(pagoActualizado.getReserva());
        }

        return pagoRepository.save(pagoExistente);
    }


    @Override
    public void eliminarPago(Integer id) {
    if(pagoRepository.existsById((id))){
        pagoRepository.deleteById(id);
    }else{
        throw new ResourceNotFoundException("Pago con ID " + id + " no encontrado.");
    }
    }
}
