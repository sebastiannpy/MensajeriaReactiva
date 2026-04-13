package com.example.chatreactivo.service;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.chatreactivo.model.Mensaje;
import com.example.chatreactivo.repository.MensajeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
public class MensajeService {

    private final MensajeRepository mensajeRepository;
    private final Sinks.Many<Mensaje> sink;

    public MensajeService(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }

    public Mono<Mensaje> enviarMensaje(Mensaje mensaje) {
        mensaje.setFechaEnvio(OffsetDateTime.now());

        return mensajeRepository.save(mensaje)
                .doOnNext(mensajeGuardado -> sink.tryEmitNext(mensajeGuardado));
    }

    public Flux<Mensaje> obtenerConversacion(UUID usuario1, UUID usuario2) {
        return mensajeRepository.obtenerConversacion(usuario1, usuario2);
    }

    public Flux<Mensaje> escucharMensajes(UUID usuarioId) {
        return sink.asFlux()
                .filter(m -> usuarioId.equals(m.getRemitenteId()) || usuarioId.equals(m.getReceptorId()));
    }
}