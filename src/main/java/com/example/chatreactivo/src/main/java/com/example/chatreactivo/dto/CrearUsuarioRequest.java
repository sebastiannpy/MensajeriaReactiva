package com.example.chatreactivo.dto;

import jakarta.validation.constraints.NotBlank;

public record CrearUsuarioRequest(
        @NotBlank(message = "El username es obligatorio")
        String username) {
}
