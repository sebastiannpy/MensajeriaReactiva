package com.example.chatreactivo.domain.ports.out;

import com.example.chatreactivo.domain.model.Mensaje;
import reactor.core.publisher.Mono;

public interface MensajeQueuePort {
    Mono<Void> publicar(Mensaje mensaje);
}