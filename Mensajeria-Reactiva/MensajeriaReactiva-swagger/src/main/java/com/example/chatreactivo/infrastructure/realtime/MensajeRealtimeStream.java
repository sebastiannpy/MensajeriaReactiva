package com.example.chatreactivo.infrastructure.realtime;

import com.example.chatreactivo.domain.model.Mensaje;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.UUID;

@Component
public class MensajeRealtimeStream {

    private final Sinks.Many<Mensaje> sink =
            Sinks.many().multicast().onBackpressureBuffer();

    public void emitir(Mensaje mensaje) {
        sink.tryEmitNext(mensaje);
    }

    public Flux<Mensaje> streamParaUsuario(UUID usuarioId) {
        return sink.asFlux()
                .filter(m -> usuarioId.equals(m.getRemitenteId())
                        || usuarioId.equals(m.getReceptorId()));
    }
}