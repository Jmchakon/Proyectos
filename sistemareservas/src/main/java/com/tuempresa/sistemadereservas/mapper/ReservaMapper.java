package com.tuempresa.sistemadereservas.mapper;

import com.tuempresa.sistemadereservas.dto.ReservaPrivateDto;
import com.tuempresa.sistemadereservas.entity.Reserva;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    ReservaPrivateDto reservaToReservaDto(Reserva reserva);
}
