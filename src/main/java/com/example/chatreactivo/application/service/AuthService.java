package com.example.chatreactivo.application.service;

import com.example.chatreactivo.domain.model.Usuario;
import com.example.chatreactivo.domain.ports.in.AuthUseCase;
import com.example.chatreactivo.domain.ports.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthService implements AuthUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public AuthService(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public Mono<Usuario> login(String username, String password) {
        return usuarioRepositoryPort.findByUsername(username)
                .switchIfEmpty(Mono.error(new RuntimeException("Usuario no encontrado")))
                .filter(Usuario::getActivo)
                .switchIfEmpty(Mono.error(new RuntimeException("Usuario inactivo")))
                .filter(usuario -> usuario.getPassword() != null && usuario.getPassword().equals(password))
                .switchIfEmpty(Mono.error(new RuntimeException("Credenciales inválidas")));
    }
}
