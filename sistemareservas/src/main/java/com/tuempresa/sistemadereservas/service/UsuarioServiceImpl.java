package com.tuempresa.sistemadereservas.service;

import com.tuempresa.sistemadereservas.entity.Usuario;
import com.tuempresa.sistemadereservas.exception.ResourceNotFoundException;
import com.tuempresa.sistemadereservas.exception.UsuarioYaExisteException;
import com.tuempresa.sistemadereservas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public Usuario crearUsuario(Usuario usuario) {
        Optional<Usuario> crearUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        if (crearUsuario.isPresent()) {
            throw new UsuarioYaExisteException("Ya existe un usuario registrado con el email: " + usuario.getEmail());
        } else {
            return usuarioRepository.save(usuario);

        }
    }

    @Override
    public Usuario obtenerUsuarioPorId(Integer id) {
        Optional<Usuario> usuario =  usuarioRepository.findById(id);;
        if(usuario.isEmpty()){
            throw new ResourceNotFoundException("Usuario con ID " + id + " no encontrado.");
        }
        return usuario.orElse(null);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        if (usuarioRepository.existsById(usuario.getIdUsuario())) {
            return usuarioRepository.save(usuario); // Actualiza el usuario
        }else{
            throw new ResourceNotFoundException("Usuario " + usuario.getIdUsuario() + " no encontrado.");
        }

    }

    @Override
    public void eliminarUsuario(Integer id) {
        if(usuarioRepository.existsById(id)){
    usuarioRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("Usuario con ID " + id + " no encontrado.");
        }
    }
}
