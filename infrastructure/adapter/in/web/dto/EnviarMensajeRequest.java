package com.example.chatreactivo.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EnviarMensajeRequest(
        @NotNull UUID remitenteId,
        @NotNull UUID receptorId,
        @NotBlank String contenido
) {
}
