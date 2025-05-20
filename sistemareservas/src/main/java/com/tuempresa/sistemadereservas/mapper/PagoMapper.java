package com.tuempresa.sistemadereservas.mapper;

import com.tuempresa.sistemadereservas.dto.PagoPrivateDto;
import com.tuempresa.sistemadereservas.entity.Pago;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PagoMapper {
    PagoPrivateDto pagoToPagoDto(Pago pago);
}
