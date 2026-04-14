package com.example.chatreactivo.domain.ports.out;

import com.example.chatreactivo.domain.model.Usuario;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UsuarioRepositoryPort {
    Mono<Usuario> save(Usuario usuario);
    Flux<Usuario> findAll();
    Mono<Usuario> findById(UUID id);
    Mono<Usuario> findByUsername(String username);
}
