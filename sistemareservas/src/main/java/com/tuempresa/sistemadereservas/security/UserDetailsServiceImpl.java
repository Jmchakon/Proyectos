package com.tuempresa.sistemadereservas.security;

import com.tuempresa.sistemadereservas.entity.Usuario;
import com.tuempresa.sistemadereservas.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar el usuario en la base de datos
        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Convertir el rol del usuario a un formato que Spring Security entiende
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(usuario.getRole().name()));

        // Devolver el usuario con los roles convertidos
        return new User(usuario.getEmail(), usuario.getPassword(), authorities);
    }
}
