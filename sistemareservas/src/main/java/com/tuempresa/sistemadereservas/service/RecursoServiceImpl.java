package com.tuempresa.sistemadereservas.service;

import com.tuempresa.sistemadereservas.entity.Recurso;
import com.tuempresa.sistemadereservas.exception.RecursoYaExistenteException;
import com.tuempresa.sistemadereservas.exception.ResourceNotFoundException;
import com.tuempresa.sistemadereservas.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RecursoServiceImpl implements RecursoService{
    @Autowired
    RecursoRepository recursoRepository;


    @Override
    public Recurso crearRecurso(Recurso recurso) {
        Optional<Recurso> recursoExistente = recursoRepository.findByNombre(recurso.getNombre());
        if (recursoExistente.isPresent()) {
            throw new RecursoYaExistenteException("Ya existe un recurso con ese nombre." + recurso.getNombre());
        } else {
            return recursoRepository.save(recurso);
        }
    }

    @Override
    public Recurso obtenerRecursoPorId(Integer id) {
       Optional<Recurso> recurso = recursoRepository.findById(id);
       if(recurso.isEmpty()){
           throw new ResourceNotFoundException("Recurso con ID " + id + " no encontrado.");
       }
       return recurso.get();
    }

    @Override
    public List<Recurso> listarRecursos() {
        return recursoRepository.findAll();
    }

    @Override
    public Recurso actualizarRecurso(Recurso recurso) {
        if(recursoRepository.existsById(recurso.getIdRecurso())){
           return recursoRepository.save(recurso);
        }else{
            throw new ResourceNotFoundException("Recurso con ID " + recurso.getIdRecurso() + " no encontrado.");
        }

    }

    @Override
    public void eliminarRecurso(Integer id) {
       if(recursoRepository.existsById(id)){
           recursoRepository.deleteById(id);
       }else{
           throw new ResourceNotFoundException("Recurso con ID " + id + " no encontrado.");
       }
    }
}
