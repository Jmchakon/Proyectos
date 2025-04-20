package com.tuempresa.sistemadereservas.mapper;

import com.tuempresa.sistemadereservas.Dto.RecursoPrivateDto;
import com.tuempresa.sistemadereservas.entity.Recurso;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecursoMapper {

    RecursoPrivateDto recursoToRecursoDto(Recurso recurso);
}
