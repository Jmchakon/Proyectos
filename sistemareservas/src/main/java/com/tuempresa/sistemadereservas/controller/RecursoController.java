package com.tuempresa.sistemadereservas.controller;

import com.tuempresa.sistemadereservas.entity.Recurso;
import com.tuempresa.sistemadereservas.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recursos")
public class RecursoController {
    @Autowired
    private RecursoService recursoService;

    @PostMapping
    public Recurso crearRecurso(@RequestBody Recurso recurso){
        return recursoService.crearRecurso(recurso);
    }
    @GetMapping("/{id}")
    public Recurso obtenerRecurso(@PathVariable Integer id){
        return recursoService.obtenerRecursoPorId(id);
    }

    @GetMapping
    public List<Recurso> listarRecursos(){
        return  recursoService.listarRecursos();
    }

    @PutMapping("/{id}")
    public Recurso actualizarRecurso(@PathVariable Integer id,@RequestBody Recurso recurso){
        recurso.setIdRecurso(id);
        return recursoService.actualizarRecurso(recurso);
    }
    @DeleteMapping("/{id}")
    public void eliminarRecurso(@PathVariable Integer id){
        recursoService.eliminarRecurso(id);
    }
}
