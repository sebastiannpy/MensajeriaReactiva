package com.example.chatreactivo.domain.ports.in;

import com.example.chatreactivo.domain.model.Rol;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface RolUseCase {
    Mono<Rol> crear(Rol rol);
    Flux<Rol> listar();
    Mono<Rol> obtenerPorId(UUID id);
    Mono<Rol> actualizar(UUID id, Rol rol);
    Mono<Void> eliminar(UUID id);
}
