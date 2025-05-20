package com.tuempresa.sistemadereservas.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateValidator {

    public static boolean isFechaInicioAntesQueFin(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return fechaInicio.isBefore(fechaFin);  // Compara las fechas y las horas
    }

    public static boolean isFechaInicioNoPasada(LocalDateTime fechaInicio) {
        return fechaInicio.isAfter(LocalDateTime.now()); // Verifica que la fecha de inicio no esté en el pasado
    }

    public static boolean isDuracionValida(LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxDias) {
        long diasEntre = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        return diasEntre <= maxDias;  // Verifica si la duración es menor o igual al límite
    }
}
