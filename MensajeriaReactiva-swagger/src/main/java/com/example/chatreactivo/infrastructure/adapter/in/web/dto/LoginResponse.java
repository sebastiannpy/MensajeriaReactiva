package com.example.chatreactivo.infrastructure.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(description = "Respuesta de autenticación exitosa")
public record LoginResponse(
        @Schema(description = "Mensaje de confirmación", example = "Inicio de sesión exitoso") String mensaje,
        @Schema(description = "UUID del usuario autenticado") UUID usuarioId,
        @Schema(description = "Nombre de usuario", example = "juan.perez") String username
) {}
