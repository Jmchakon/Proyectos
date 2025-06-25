package com.tuempresa.sistemadereservas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;
    private double monto;
    private LocalDateTime fechaPago;
    private String metodoPago;
    private String estadoPago;
    @Column(unique = true)
    private String referenciaPago;
    @ManyToOne
    @JoinColumn(name = "idReserva", nullable = false)
    @JsonManagedReference
    private Reserva reserva;
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;
}
