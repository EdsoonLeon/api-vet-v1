package com.testvet.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class PasswordEncoderConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .formLogin(login -> login.disable())
                .httpBasic(basic -> {})
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/login/login", "/api/login/registrar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/usuarios/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/usuarios/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/usuarios/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/usuarios/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/clientes/registrar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/clientes/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/clientes/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/clientes/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/clientes/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/veterinarios/registrar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/veterinarios/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/veterinarios/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/veterinarios/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/veterinarios/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/mascotas/registrar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/mascotas/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/mascotas/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/mascotas/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/mascotas/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/citas/registrar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/citas/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/citas/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/citas/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/citas/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/consultas/registrar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/consultas/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/consultas/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/consultas/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/roles").permitAll()
                        .anyRequest().authenticated());
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}