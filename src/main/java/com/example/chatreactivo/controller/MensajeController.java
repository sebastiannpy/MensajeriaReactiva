package com.example.chatreactivo.controller;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.chatreactivo.model.Mensaje;
import com.example.chatreactivo.service.MensajeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    private final MensajeService mensajeService;

    public MensajeController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    @PostMapping
    public Mono<Mensaje> enviarMensaje(@RequestBody Mensaje mensaje) {
        return mensajeService.enviarMensaje(mensaje);
    }

    @GetMapping
    public Flux<Mensaje> obtenerConversacion(@RequestParam UUID usuario1, @RequestParam UUID usuario2) {
        return mensajeService.obtenerConversacion(usuario1, usuario2);
    }

    @GetMapping(value = "/stream/{usuarioId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Mensaje> escucharMensajes(@PathVariable UUID usuarioId) {
        return mensajeService.escucharMensajes(usuarioId);
    }
}
