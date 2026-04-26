package com.example.chatreactivo.infrastructure.adapter.in.web;

import com.example.chatreactivo.domain.ports.in.AuthUseCase;
import com.example.chatreactivo.infrastructure.adapter.in.web.dto.LoginRequest;
import com.example.chatreactivo.infrastructure.adapter.in.web.dto.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Endpoints para autenticación de usuarios")
public class AuthController {

    private final AuthUseCase authUseCase;

    public AuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @Operation(
        summary = "Iniciar sesión",
        description = "Autentica un usuario con su username y contraseña. Retorna los datos del usuario si las credenciales son correctas."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = LoginResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos", content = @Content),
        @ApiResponse(responseCode = "401", description = "Credenciales incorrectas", content = @Content)
    })
    @PostMapping("/login")
    public Mono<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return authUseCase.login(request.username(), request.password())
                .map(usuario -> new LoginResponse("Inicio de sesión exitoso", usuario.getId(), usuario.getUsername()));
    }
}
