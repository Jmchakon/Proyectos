package com.tuempresa.sistemadereservas.service;

import com.tuempresa.sistemadereservas.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario crearUsuario(Usuario usuario);
    Usuario obtenerUsuarioPorId(Integer id);
    List<Usuario> listarUsuarios();
    Usuario actualizarUsuario(Usuario usuario);
    void eliminarUsuario(Integer id);
}
