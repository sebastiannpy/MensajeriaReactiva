package com.example.chatreactivo.infrastructure.adapter.in.web.dto;

import java.util.UUID;

public record LoginResponse(
        String mensaje,
        UUID usuarioId,
        String username
) {
}
