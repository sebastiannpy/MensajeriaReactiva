package com.example.chatreactivo.infrastructure.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Schema(description = "Datos del mensaje a enviar entre usuarios")
public record EnviarMensajeRequest(
        @Schema(description = "UUID del usuario remitente") @NotNull UUID remitenteId,
        @Schema(description = "UUID del usuario receptor") @NotNull UUID receptorId,
        @Schema(description = "Contenido del mensaje", example = "Hola, ¿cómo estás?") @NotBlank String contenido
) {}
