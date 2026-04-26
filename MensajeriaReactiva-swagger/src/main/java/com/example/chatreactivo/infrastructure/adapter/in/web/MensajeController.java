package com.example.chatreactivo.infrastructure.adapter.in.web;

import com.example.chatreactivo.domain.model.Mensaje;
import com.example.chatreactivo.domain.ports.in.MensajeUseCase;
import com.example.chatreactivo.infrastructure.adapter.in.web.dto.EnviarMensajeRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/mensajes")
@Tag(name = "Mensajes", description = "Envío y consulta de mensajes entre usuarios. Incluye soporte reactivo con SSE (Server-Sent Events)")
public class MensajeController {

    private final MensajeUseCase mensajeUseCase;

    public MensajeController(MensajeUseCase mensajeUseCase) {
        this.mensajeUseCase = mensajeUseCase;
    }

    @Operation(summary = "Enviar mensaje", description = "Envía un mensaje de un usuario a otro")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Mensaje enviado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = Mensaje.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o usuario inexistente", content = @Content)
    })
    @PostMapping
    public Mono<Mensaje> enviar(@Valid @RequestBody EnviarMensajeRequest request) {
        Mensaje mensaje = new Mensaje();
        mensaje.setRemitenteId(request.remitenteId());
        mensaje.setReceptorId(request.receptorId());
        mensaje.setContenido(request.contenido());
        return mensajeUseCase.enviar(mensaje);
    }

    @Operation(
        summary = "Obtener conversación",
        description = "Retorna el historial de mensajes entre dos usuarios ordenado cronológicamente"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Conversación obtenida exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(schema = @Schema(implementation = Mensaje.class)))),
        @ApiResponse(responseCode = "400", description = "IDs de usuario inválidos", content = @Content)
    })
    @GetMapping
    public Flux<Mensaje> obtenerConversacion(
        @Parameter(description = "UUID del primer usuario", required = true) @RequestParam UUID usuario1,
        @Parameter(description = "UUID del segundo usuario", required = true) @RequestParam UUID usuario2) {
        return mensajeUseCase.obtenerConversacion(usuario1, usuario2);
    }

    @Operation(
        summary = "Escuchar mensajes en tiempo real (SSE)",
        description = "Abre un stream de Server-Sent Events para recibir mensajes nuevos de forma reactiva en tiempo real para un usuario dado"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Stream de mensajes activo",
            content = @Content(mediaType = MediaType.TEXT_EVENT_STREAM_VALUE,
                schema = @Schema(implementation = Mensaje.class)))
    })
    @GetMapping(value = "/stream/{usuarioId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Mensaje> escucharMensajes(
        @Parameter(description = "UUID del usuario que escucha mensajes", required = true) @PathVariable UUID usuarioId) {
        return mensajeUseCase.escucharMensajes(usuarioId);
    }
}
