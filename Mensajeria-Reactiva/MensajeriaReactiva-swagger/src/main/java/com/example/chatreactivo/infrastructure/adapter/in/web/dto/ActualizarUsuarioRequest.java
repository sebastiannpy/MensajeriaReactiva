package com.example.chatreactivo.infrastructure.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

@Schema(description = "Datos para actualizar un usuario existente")
public record ActualizarUsuarioRequest(
        @Schema(description = "Nueva contraseña del usuario", example = "nuevaPassword456") @NotBlank String password,
        @Schema(description = "Nuevo nombre completo", example = "Juan Carlos Pérez") @NotBlank String nombre,
        @Schema(description = "Nuevo correo electrónico", example = "juancarlos@example.com") @NotBlank String correo,
        @Schema(description = "UUID del nuevo rol asignado (opcional)") UUID rolId
) {}
