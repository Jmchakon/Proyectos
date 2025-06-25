package com.tuempresa.sistemadereservas.controller;

import com.tuempresa.sistemadereservas.entity.Usuario;
import com.tuempresa.sistemadereservas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
        public List<Usuario> ListarUsuarios(){
            return usuarioService.listarUsuarios();
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
