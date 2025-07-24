package com.tuempresa.sistemadereservas.controller;

import com.tuempresa.sistemadereservas.entity.Usuario;
import com.tuempresa.sistemadereservas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {


        @Autowired
        private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario creado = usuarioService.crearUsuario(usuario);
            return ResponseEntity.ok(creado);
        } catch (Exception e) {
            e.printStackTrace();  // Esto imprimirá el error completo en la consola
            return ResponseEntity.status(500).body("Error al crear usuario: " + e.getMessage());
        }
    }
        @GetMapping("/{id}")
        public Usuario obtenerUsuario(@PathVariable Integer id){
        return  usuarioService.obtenerUsuarioPorId(id);
        }

    @GetMapping
    public ResponseEntity<?> listarUsuarios(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).build();
        }

        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));

        String email = authentication.getName();

        if (isAdmin) {
            // Usuario con rol admin -> devuelve todos los usuarios
            List<Usuario> usuarios = usuarioService.listarUsuarios();
            return ResponseEntity.ok(usuarios);
        } else {
            // Usuario normal -> devuelve solo su usuario
            Usuario usuario = usuarioService.obtenerUsuarioPorEmail(email);
            if (usuario == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(List.of(usuario));
        }
    }

        @PutMapping("/{id}")
        public Usuario actualizarUsuario(@PathVariable Integer id,@RequestBody Usuario usuario){
            usuario.setIdUsuario(id);
            return usuarioService.actualizarUsuario(usuario);
        }

        @DeleteMapping("/{id}")
        public void eliminarUsuario(@PathVariable Integer id){
            usuarioService.eliminarUsuario(id);
        }



}
