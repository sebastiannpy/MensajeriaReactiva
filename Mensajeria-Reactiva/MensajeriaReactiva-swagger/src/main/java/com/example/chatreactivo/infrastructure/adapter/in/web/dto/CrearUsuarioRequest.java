package com.example.chatreactivo.infrastructure.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

@Schema(description = "Datos necesarios para registrar un nuevo usuario")
public record CrearUsuarioRequest(
        @Schema(description = "Nombre de usuario único", example = "juan.perez") @NotBlank String username,
        @Schema(description = "Contraseña del usuario", example = "miPassword123") @NotBlank String password,
        @Schema(description = "Nombre completo del usuario", example = "Juan Pérez") @NotBlank String nombre,
        @Schema(description = "Correo electrónico del usuario", example = "juan@example.com") @NotBlank String correo,
        @Schema(description = "UUID del rol asignado al usuario (opcional)") UUID rolId
) {}
