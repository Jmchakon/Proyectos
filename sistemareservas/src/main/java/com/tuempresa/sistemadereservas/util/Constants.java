package com.tuempresa.sistemadereservas.util;

import java.util.List;

public class Constants {

    // Constantes de los estados de las reservas
    public static final String RESERVA_ESTADO_PENDIENTE = "PENDIENTE";
    public static final String RESERVA_ESTADO_CONFIRMADA = "CONFIRMADA";
    public static final String RESERVA_ESTADO_CANCELADA = "CANCELADA";

    public static final List<String> ESTADOS_RESERVA = List.of(
            RESERVA_ESTADO_PENDIENTE,
            RESERVA_ESTADO_CONFIRMADA,
            RESERVA_ESTADO_CANCELADA
    );

    // Constantes de roles de usuario
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    // Constantes de tipos de recursos
    // Habitaciones
    public static final String TIPO_RECURSO_HABITACION_SIMPLE = "HABITACION_SIMPLE"; // Cama simple
    public static final String TIPO_RECURSO_HABITACION_DOBLE = "HABITACION_DOBLE";   // Cama doble

    // Mesas
    public static final String TIPO_RECURSO_MESA_SALA = "MESA_SALA";     // Mesa en sala
    public static final String TIPO_RECURSO_MESA_TERRAZA = "MESA_TERRAZA"; // Mesa en terraza

    // Salas grandes o pequeñas
    public static final String TIPO_RECURSO_SALA_GRANDE = "SALA_GRANDE";   // Sala grande
    public static final String TIPO_RECURSO_SALA_PEQUENA = "SALA_PEQUENA"; // Sala pequeña
}
