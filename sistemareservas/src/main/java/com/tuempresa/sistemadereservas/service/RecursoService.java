package com.tuempresa.sistemadereservas.service;

import com.tuempresa.sistemadereservas.entity.Recurso;

import java.util.List;

public interface RecursoService {
    Recurso crearRecurso(Recurso recurso);
    Recurso obtenerRecursoPorId(Integer id);
    List<Recurso> listarRecursos();
    Recurso actualizarRecurso(Recurso recurso);
    void eliminarRecurso(Integer id);
}
