package com.tuempresa.sistemadereservas.controller;

import com.tuempresa.sistemadereservas.entity.Reserva;
import com.tuempresa.sistemadereservas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public Reserva crearReserva(@RequestBody Reserva reserva){
        return reservaService.crearReserva(reserva);
    }

    @GetMapping("/{id}")
    public Reserva obtenerReserva(@PathVariable Integer id){
        return reservaService.obtenerReservaPorId(id);
    }

    @GetMapping
    public List<Reserva> listarReservas(){
        return reservaService.listarReservas();
    }

    @PutMapping("/{id}")
    public Reserva actualizarReserva(@PathVariable Integer id,@RequestBody Reserva reserva){
        reserva.setIdReserva(id);
        return reservaService.actualizarReserva(reserva);
    }

    @DeleteMapping("/{id}")
    public void eliminarReserva(@PathVariable Integer id){
        reservaService.eliminarReserva(id);


    }
    @GetMapping("/{id}/total")
    public double obtenerTotalReserva(@PathVariable Integer id) {
        return reservaService.obtenerTotalReserva(id);
    }

}
