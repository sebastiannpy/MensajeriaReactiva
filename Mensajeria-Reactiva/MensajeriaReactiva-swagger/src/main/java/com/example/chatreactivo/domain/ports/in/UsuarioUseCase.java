package com.example.chatreactivo.domain.ports.in;

import com.example.chatreactivo.domain.model.Usuario;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UsuarioUseCase {
    Mono<Usuario> registrar(Usuario usuario);
    Flux<Usuario> listar();
    Mono<Usuario> obtenerPorId(UUID id);
    Mono<Usuario> actualizar(UUID id, Usuario usuario);
    Mono<Usuario> inactivar(UUID id);
}
