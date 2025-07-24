package com.tuempresa.sistemadereservas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Enumerated(EnumType.STRING)
    private TipoRecurso tipoDeRecurso;

    public enum TipoRecurso {
        TERRAZA,
        SALA_RESTAURANTE,
        HABITACION_1_CAMA,
        HABITACION_2_CAMAS,
        SALA_GRANDE,
        SALA_PEQUENA
    }


    @ManyToMany(mappedBy = "recursos",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reserva> reservas;
    private double precio;
    @Column(unique = true, nullable = false)
    private String nombre;
}

