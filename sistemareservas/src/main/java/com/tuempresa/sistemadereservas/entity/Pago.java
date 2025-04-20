package com.tuempresa.sistemadereservas.entity;

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
    private Reserva reserva;
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
}
