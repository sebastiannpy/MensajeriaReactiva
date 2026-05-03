package com.example.chatreactivo.application.service;

import com.example.chatreactivo.domain.model.Mensaje;
import com.example.chatreactivo.domain.ports.in.MensajeUseCase;
import com.example.chatreactivo.domain.ports.out.MensajeQueuePort;
import com.example.chatreactivo.domain.ports.out.MensajeRepositoryPort;
import com.example.chatreactivo.infrastructure.realtime.MensajeRealtimeStream;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class MensajeService implements MensajeUseCase {

    private final MensajeRepositoryPort mensajeRepositoryPort;
    private final MensajeQueuePort mensajeQueuePort;
    private final MensajeRealtimeStream mensajeRealtimeStream;

    public MensajeService(
            MensajeRepositoryPort mensajeRepositoryPort,
            MensajeQueuePort mensajeQueuePort,
            MensajeRealtimeStream mensajeRealtimeStream
    ) {
        this.mensajeRepositoryPort = mensajeRepositoryPort;
        this.mensajeQueuePort = mensajeQueuePort;
        this.mensajeRealtimeStream = mensajeRealtimeStream;
    }

    @Override
    public Mono<Mensaje> enviar(Mensaje mensaje) {
        mensaje.setFechaEnvio(OffsetDateTime.now());

        return mensajeRepositoryPort.save(mensaje)
                .flatMap(guardado -> mensajeQueuePort.publicar(guardado).thenReturn(guardado))
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
        return mensajeRealtimeStream.streamParaUsuario(usuarioId);
    }
}