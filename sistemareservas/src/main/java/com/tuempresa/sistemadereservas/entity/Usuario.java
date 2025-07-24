package com.tuempresa.sistemadereservas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "usuario-reservas")
    private List<Reserva> reservas = new ArrayList<>();
    @Column(nullable = false)
    private String nombreCompleto;
    @Column(nullable = false)
    private Integer numTelefono;
    @Column(unique = true, nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference(value = "usuario-pagos")
    private List<Pago> pagos;


}
