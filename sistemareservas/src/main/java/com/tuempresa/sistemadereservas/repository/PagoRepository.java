package com.tuempresa.sistemadereservas.repository;

import com.tuempresa.sistemadereservas.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PagoRepository extends JpaRepository<Pago, Integer>{
    Optional<Pago> findByReferenciaPagoAndReserva_id(String referenciaDePago,Integer idReserva);

}
