package com.example.chatreactivo.application.service;

import com.example.chatreactivo.domain.model.Usuario;
import com.example.chatreactivo.domain.ports.in.AuthUseCase;
import com.example.chatreactivo.domain.ports.out.UsuarioRepositoryPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthService implements AuthUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UsuarioRepositoryPort usuarioRepositoryPort,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<Usuario> login(String username, String password) {
        return usuarioRepositoryPort.findByUsername(username)
                .switchIfEmpty(Mono.error(new RuntimeException("Usuario no encontrado")))
                .filter(Usuario::getActivo)
                .switchIfEmpty(Mono.error(new RuntimeException("Usuario inactivo")))
                .filter(usuario -> usuario.getPassword() != null &&
                        passwordEncoder.matches(password, usuario.getPassword()))
                .switchIfEmpty(Mono.error(new RuntimeException("Credenciales inválidas")));
    }
}