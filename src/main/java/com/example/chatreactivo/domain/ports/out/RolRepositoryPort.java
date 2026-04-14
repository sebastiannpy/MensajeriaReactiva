package com.example.chatreactivo.domain.ports.out;

import com.example.chatreactivo.domain.model.Rol;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface RolRepositoryPort {
    Mono<Rol> save(Rol rol);
    Flux<Rol> findAll();
    Mono<Rol> findById(UUID id);
    Mono<Void> deleteById(UUID id);
    Mono<Boolean> existsById(UUID id);
}
