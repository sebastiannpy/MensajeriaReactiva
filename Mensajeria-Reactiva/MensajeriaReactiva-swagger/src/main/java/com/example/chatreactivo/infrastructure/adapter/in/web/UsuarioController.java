package com.example.chatreactivo.infrastructure.adapter.in.web;

import com.example.chatreactivo.domain.model.Usuario;
import com.example.chatreactivo.domain.ports.in.UsuarioUseCase;
import com.example.chatreactivo.infrastructure.adapter.in.web.dto.ActualizarUsuarioRequest;
import com.example.chatreactivo.infrastructure.adapter.in.web.dto.CrearUsuarioRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Gestión de usuarios del sistema de mensajería")
public class UsuarioController {

    private final UsuarioUseCase usuarioUseCase;

    public UsuarioController(UsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }

    @Operation(summary = "Registrar usuario", description = "Crea un nuevo usuario en el sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o username ya existente", content = @Content)
    })
    @PostMapping
    public Mono<Usuario> registrar(@Valid @RequestBody CrearUsuarioRequest request) {
        Usuario usuario = new Usuario(null, request.username(), request.password(), request.nombre(), request.correo(), true, request.rolId());
        return usuarioUseCase.registrar(usuario);
    }

    @Operation(summary = "Listar usuarios", description = "Retorna todos los usuarios registrados en el sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(schema = @Schema(implementation = Usuario.class))))
    })
    @GetMapping
    public Flux<Usuario> listar() {
        return usuarioUseCase.listar();
    }

    @Operation(summary = "Obtener usuario por ID", description = "Retorna los datos de un usuario específico")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario encontrado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public Mono<Usuario> obtenerPorId(
        @Parameter(description = "UUID del usuario", required = true) @PathVariable UUID id) {
        return usuarioUseCase.obtenerPorId(id);
    }

    @Operation(summary = "Actualizar usuario", description = "Actualiza los datos de un usuario existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public Mono<Usuario> actualizar(
        @Parameter(description = "UUID del usuario", required = true) @PathVariable UUID id,
        @Valid @RequestBody ActualizarUsuarioRequest request) {
        Usuario usuario = new Usuario(id, null, request.password(), request.nombre(), request.correo(), true, request.rolId());
        return usuarioUseCase.actualizar(id, usuario);
    }

    @Operation(summary = "Inactivar usuario", description = "Cambia el estado del usuario a inactivo (baja lógica)")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario inactivado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })
    @PatchMapping("/{id}/inactivar")
    public Mono<Usuario> inactivar(
        @Parameter(description = "UUID del usuario a inactivar", required = true) @PathVariable UUID id) {
        return usuarioUseCase.inactivar(id);
    }
}
