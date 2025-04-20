package com.tuempresa.sistemadereservas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReserva;
    @ManyToOne
    @JoinColumn(name = "idUsuario" ,  nullable = false )
    private Usuario usuario;
    @ManyToMany
    @JoinTable(
            name = "recursoReserva",
            joinColumns = @JoinColumn(name = "idReserva"),
            inverseJoinColumns = @JoinColumn(name ="idRecurso")
    )
    private List<Recurso> recursos;
    private LocalDateTime fechaReservacion;
    private String estadoReservacion;
    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
    private List<Pago> pago;
}
