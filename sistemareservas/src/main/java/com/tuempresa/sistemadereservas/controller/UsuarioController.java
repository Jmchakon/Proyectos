package com.tuempresa.sistemadereservas.controller;

import com.tuempresa.sistemadereservas.entity.Usuario;
import com.tuempresa.sistemadereservas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {


        @Autowired
        private UsuarioService usuarioService;

        @PostMapping
        public  Usuario crearUsuario(@RequestBody Usuario usuario){
            return usuarioService.crearUsuario(usuario);
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
