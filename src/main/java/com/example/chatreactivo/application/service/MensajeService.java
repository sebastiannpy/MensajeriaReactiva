package com.example.chatreactivo.application.service;

import com.example.chatreactivo.domain.model.Mensaje;
import com.example.chatreactivo.domain.ports.in.MensajeUseCase;
import com.example.chatreactivo.domain.ports.out.MensajeRepositoryPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class MensajeService implements MensajeUseCase {

    private final MensajeRepositoryPort mensajeRepositoryPort;
    private final Sinks.Many<Mensaje> sink;

    public MensajeService(MensajeRepositoryPort mensajeRepositoryPort) {
        this.mensajeRepositoryPort = mensajeRepositoryPort;
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }

    @Override
    public Mono<Mensaje> enviar(Mensaje mensaje) {
        mensaje.setFechaEnvio(OffsetDateTime.now());
        return mensajeRepositoryPort.save(mensaje)
                .doOnNext(guardado -> sink.tryEmitNext(guardado))
                .onErrorResume(error -> Mono.error(new RuntimeException("No fue posible enviar el mensaje")));
    }

    @Override
    public Flux<Mensaje> obtenerConversacion(UUID usuario1, UUID usuario2) {
        return mensajeRepositoryPort.obtenerConversacion(usuario1, usuario2)
                .collectList()
                .flatMapMany(Flux::fromIterable);
    }

    @Override
    public Flux<Mensaje> escucharMensajes(UUID usuarioId) {
        return sink.asFlux()
                .filter(m -> usuarioId.equals(m.getRemitenteId()) || usuarioId.equals(m.getReceptorId()));
    }
}
