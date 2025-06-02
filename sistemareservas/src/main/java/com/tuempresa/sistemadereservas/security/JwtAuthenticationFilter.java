package com.tuempresa.sistemadereservas.security;

import com.tuempresa.sistemadereservas.entity.Usuario;
import com.tuempresa.sistemadereservas.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

                String authHeader = request.getHeader("Authorization");

                if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
                    String token = authHeader.substring(7);
                    String username = jwtUtil.extractUsername(token);

                    if (jwtUtil.validateToken(token, username)) {
                        // Obtenemos el usuario de la base de datos
                        Usuario usuario = usuarioRepository.findByEmail(username)
                                .orElse(null);

                        if (usuario != null) {
                            // Convertimos el rol en una autoridad de Spring
                            SimpleGrantedAuthority authority =
                                    new SimpleGrantedAuthority("ROLE_" + usuario.getRole().name());

                            UsernamePasswordAuthenticationToken authenticationToken =
                                    new UsernamePasswordAuthenticationToken(
                                            username,
                                            null,
                                            List.of(authority) // Aquí metemos el rol como permiso
                                    );

                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        }
                    }
                }

                filterChain.doFilter(request, response);
            }
        }



