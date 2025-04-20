package com.tuempresa.sistemadereservas.service;

import com.tuempresa.sistemadereservas.entity.Pago;
import com.tuempresa.sistemadereservas.exception.PagoYaRealizadoException;
import com.tuempresa.sistemadereservas.exception.ResourceNotFoundException;
import com.tuempresa.sistemadereservas.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoServiceImpl implements PagoService {
    @Autowired
    PagoRepository pagoRepository;


    @Override
    public Pago crearPago(Pago pago) {
        Optional<Pago> crearPago = pagoRepository.findByReferenciaPagoAndReserva_id(pago.getReferenciaPago(),pago.getReserva().getIdReserva());
        if(crearPago.isPresent()){
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
    public Pago actualizarPago(Pago pago) {
        if(pagoRepository.existsById(pago.getIdPago())){
         return    pagoRepository.save(pago);
        }else{
            throw new ResourceNotFoundException("Pago" + pago.getIdPago() + " no encontrado.");
        }

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
