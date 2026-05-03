package com.example.chatreactivo.infrastructure.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Credenciales para iniciar sesión")
public record LoginRequest(
        @Schema(description = "Nombre de usuario", example = "juan.perez") @NotBlank String username,
        @Schema(description = "Contraseña del usuario", example = "miPassword123") @NotBlank String password
) {}
