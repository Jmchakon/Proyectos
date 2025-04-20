package com.tuempresa.sistemadereservas.repository;

import com.tuempresa.sistemadereservas.entity.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecursoRepository extends JpaRepository<Recurso,Integer> {
    Optional<Recurso> findByNombre(String nombre);

}
