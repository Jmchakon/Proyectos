package com.tuempresa.sistemadereservas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Recurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRecurso;
    private String tipoDeRecurso;
    @ManyToMany(mappedBy = "recurso",cascade = CascadeType.ALL)
    private List<Reserva> reservas;
    private double precio;
    @Column(unique = true, nullable = false)
    private String nombre;
}

