package com.example.chatreactivo.domain.ports.in;

import com.example.chatreactivo.domain.model.Usuario;
import reactor.core.publisher.Mono;

public interface AuthUseCase {
    Mono<Usuario> login(String username, String password);
}
