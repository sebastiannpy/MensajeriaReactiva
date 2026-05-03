package com.example.chatreactivo.infrastructure.adapter.in.web;

import com.example.chatreactivo.domain.model.Rol;
import com.example.chatreactivo.domain.ports.in.RolUseCase;
import com.example.chatreactivo.infrastructure.adapter.in.web.dto.CrearRolRequest;
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
@RequestMapping("/api/roles")
@Tag(name = "Roles", description = "Gestión de roles de usuario")
public class RolController {

    private final RolUseCase rolUseCase;

    public RolController(RolUseCase rolUseCase) {
        this.rolUseCase = rolUseCase;
    }

    @Operation(summary = "Crear rol", description = "Crea un nuevo rol en el sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rol creado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = Rol.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @PostMapping
    public Mono<Rol> crear(@Valid @RequestBody CrearRolRequest request) {
        Rol rol = new Rol(null, request.nombre(), request.descripcion(), request.activo());
        return rolUseCase.crear(rol);
    }

    @Operation(summary = "Listar roles", description = "Retorna todos los roles registrados")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de roles obtenida exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(schema = @Schema(implementation = Rol.class))))
    })
    @GetMapping
    public Flux<Rol> listar() {
        return rolUseCase.listar();
    }

    @Operation(summary = "Obtener rol por ID", description = "Retorna un rol específico por su UUID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rol encontrado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = Rol.class))),
        @ApiResponse(responseCode = "404", description = "Rol no encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public Mono<Rol> obtener(
        @Parameter(description = "UUID del rol", required = true) @PathVariable UUID id) {
        return rolUseCase.obtenerPorId(id);
    }

    @Operation(summary = "Actualizar rol", description = "Actualiza los datos de un rol existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rol actualizado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = Rol.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Rol no encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public Mono<Rol> actualizar(
        @Parameter(description = "UUID del rol", required = true) @PathVariable UUID id,
        @Valid @RequestBody CrearRolRequest request) {
        Rol rol = new Rol(id, request.nombre(), request.descripcion(), request.activo());
        return rolUseCase.actualizar(id, rol);
    }

    @Operation(summary = "Eliminar rol", description = "Elimina permanentemente un rol del sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rol eliminado exitosamente", content = @Content),
        @ApiResponse(responseCode = "404", description = "Rol no encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public Mono<Void> eliminar(
        @Parameter(description = "UUID del rol a eliminar", required = true) @PathVariable UUID id) {
        return rolUseCase.eliminar(id);
    }
}
