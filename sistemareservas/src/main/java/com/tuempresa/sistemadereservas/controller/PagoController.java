package com.tuempresa.sistemadereservas.controller;

import com.tuempresa.sistemadereservas.entity.Pago;
import com.tuempresa.sistemadereservas.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {
    @Autowired
    private PagoService pagoService;

    @PostMapping
    public Pago crearPago(@RequestBody Pago pago){
        return pagoService.crearPago(pago);
    }
    @GetMapping("/{id}")
    public Pago obtenerPago(@PathVariable Integer id){
        return pagoService.obtenerPagoPorId(id);
    }
    @GetMapping
    public List<Pago> listarPagos(){
        return  pagoService.listarPagos();
    }

    @PutMapping("/{id}")
    public  Pago actualizarPago(@PathVariable Integer id, @RequestBody Pago pago){
        return pagoService.actualizarPago(pago);
    }
    @DeleteMapping("/{id}")
    public void eliminarPago(@PathVariable Integer id){
        pagoService.eliminarPago(id);
    }


}
