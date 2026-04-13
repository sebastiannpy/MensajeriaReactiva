package com.example.chatreactivo.domain.ports.in;

import com.example.chatreactivo.domain.model.Mensaje;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface MensajeUseCase {
    Mono<Mensaje> enviar(Mensaje mensaje);
    Flux<Mensaje> obtenerConversacion(UUID usuario1, UUID usuario2);
    Flux<Mensaje> escucharMensajes(UUID usuarioId);
}
