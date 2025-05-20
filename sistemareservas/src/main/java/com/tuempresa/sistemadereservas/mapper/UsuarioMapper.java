package com.tuempresa.sistemadereservas.mapper;
import org.mapstruct.Mapper;
import com.tuempresa.sistemadereservas.dto.UsuarioPrivateDto;
import com.tuempresa.sistemadereservas.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioPrivateDto usuarioToUsuatioDto(Usuario usuario);

}
