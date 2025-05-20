package com.tuempresa.sistemadereservas.security;

import com.tuempresa.sistemadereservas.security.JwtAuthenticationFilter;
import com.tuempresa.sistemadereservas.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;  // Filtro que ya creaste
    private final UserDetailsServiceImpl userDetailsService;       // Carga el usuario desde la base de datos

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) // Desactivamos CSRF porque usamos JWT, no sesiones
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Sin sesión: usamos tokens
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register").permitAll() // Permitir acceso libre
                        .requestMatchers("/admin/**").hasRole("ADMIN")     // Solo para ADMIN
                        .requestMatchers("/user/**").hasRole("USER")       // Solo para USER
                        .anyRequest().authenticated()                     // Todo lo demás requiere login
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Añadimos el filtro JWT
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Para encriptar contraseñas
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager(); // Spring lo necesita para validar usuarios
    }
}
