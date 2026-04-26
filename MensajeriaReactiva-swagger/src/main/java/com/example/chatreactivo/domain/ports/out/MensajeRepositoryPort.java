package com.example.chatreactivo.domain.ports.out;

import com.example.chatreactivo.domain.model.Mensaje;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface MensajeRepositoryPort {
    Mono<Mensaje> save(Mensaje mensaje);
    Flux<Mensaje> obtenerConversacion(UUID usuario1, UUID usuario2);
}
