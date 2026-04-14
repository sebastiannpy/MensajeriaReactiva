package com.example.chatreactivo.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnviarMensajeRequest(
        @NotNull(message = "El remitenteId es obligatorio")
        UUID remitenteId,

        @NotNull(message = "El receptorId es obligatorio")
        UUID receptorId,

        @NotBlank(message = "El contenido es obligatorio")
        String contenido) {
}
