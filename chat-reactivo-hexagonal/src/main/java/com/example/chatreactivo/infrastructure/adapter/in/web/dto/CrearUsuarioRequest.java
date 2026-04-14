package com.example.chatreactivo.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CrearUsuarioRequest(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String nombre,
        @NotBlank String correo,
        UUID rolId
) {
}
