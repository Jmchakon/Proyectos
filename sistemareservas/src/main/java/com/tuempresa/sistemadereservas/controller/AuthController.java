package com.tuempresa.sistemadereservas.controller;

import com.tuempresa.sistemadereservas.dto.AuthRequest;
import com.tuempresa.sistemadereservas.dto.AuthResponse;
import com.tuempresa.sistemadereservas.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        try {
            // 1. Autenticamos al usuario con su email y contraseña
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            // 2. Si llegamos aquí, es que la autenticación fue exitosa.
            String username = authentication.getName();

            // 3. Si llegamos aquí, es que la autenticación fue exitosa.
            String token = jwtUtil.generateToken(username);

            // 4. Devolvemos el token
            return new AuthResponse(token);

        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciales inválidas");
        }
    }
}
