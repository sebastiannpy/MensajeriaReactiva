package com.example.chatreactivo.infrastructure.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Datos necesarios para crear un nuevo rol")
public record CrearRolRequest(
        @Schema(description = "Nombre del rol", example = "ADMIN") @NotBlank String nombre,
        @Schema(description = "Descripción del rol", example = "Administrador del sistema") String descripcion,
        @Schema(description = "Indica si el rol está activo", example = "true") Boolean activo
) {}
