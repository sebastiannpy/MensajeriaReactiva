package com.example.chatreactivo.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record CrearRolRequest(
        @NotBlank String nombre,
        String descripcion,
        Boolean activo
) {
}
