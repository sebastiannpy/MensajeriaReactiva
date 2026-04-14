package com.example.chatreactivo.infrastructure.adapter.in.web;

import com.example.chatreactivo.domain.ports.in.AuthUseCase;
import com.example.chatreactivo.infrastructure.adapter.in.web.dto.LoginRequest;
import com.example.chatreactivo.infrastructure.adapter.in.web.dto.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthUseCase authUseCase;

    public AuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping("/login")
    public Mono<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return authUseCase.login(request.username(), request.password())
                .map(usuario -> new LoginResponse("Inicio de sesión exitoso", usuario.getId(), usuario.getUsername()));
    }
}
