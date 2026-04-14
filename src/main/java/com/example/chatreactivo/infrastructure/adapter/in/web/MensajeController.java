package com.example.chatreactivo.infrastructure.adapter.in.web;

import com.example.chatreactivo.domain.model.Mensaje;
import com.example.chatreactivo.domain.ports.in.MensajeUseCase;
import com.example.chatreactivo.infrastructure.adapter.in.web.dto.EnviarMensajeRequest;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    private final MensajeUseCase mensajeUseCase;

    public MensajeController(MensajeUseCase mensajeUseCase) {
        this.mensajeUseCase = mensajeUseCase;
    }

    @PostMapping
    public Mono<Mensaje> enviar(@Valid @RequestBody EnviarMensajeRequest request) {
        Mensaje mensaje = new Mensaje();
        mensaje.setRemitenteId(request.remitenteId());
        mensaje.setReceptorId(request.receptorId());
        mensaje.setContenido(request.contenido());
        return mensajeUseCase.enviar(mensaje);
    }

    @GetMapping
    public Flux<Mensaje> obtenerConversacion(@RequestParam UUID usuario1, @RequestParam UUID usuario2) {
        return mensajeUseCase.obtenerConversacion(usuario1, usuario2);
    }

    @GetMapping(value = "/stream/{usuarioId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Mensaje> escucharMensajes(@PathVariable UUID usuarioId) {
        return mensajeUseCase.escucharMensajes(usuarioId);
    }
}
