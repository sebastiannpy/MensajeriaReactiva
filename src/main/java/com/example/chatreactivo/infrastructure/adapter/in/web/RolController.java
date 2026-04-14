package com.example.chatreactivo.infrastructure.adapter.in.web;

import com.example.chatreactivo.domain.model.Rol;
import com.example.chatreactivo.domain.ports.in.RolUseCase;
import com.example.chatreactivo.infrastructure.adapter.in.web.dto.CrearRolRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolUseCase rolUseCase;

    public RolController(RolUseCase rolUseCase) {
        this.rolUseCase = rolUseCase;
    }

    @PostMapping
    public Mono<Rol> crear(@Valid @RequestBody CrearRolRequest request) {
        Rol rol = new Rol(null, request.nombre(), request.descripcion(), request.activo());
        return rolUseCase.crear(rol);
    }

    @GetMapping
    public Flux<Rol> listar() {
        return rolUseCase.listar();
    }

    @GetMapping("/{id}")
    public Mono<Rol> obtener(@PathVariable UUID id) {
        return rolUseCase.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Mono<Rol> actualizar(@PathVariable UUID id, @Valid @RequestBody CrearRolRequest request) {
        Rol rol = new Rol(id, request.nombre(), request.descripcion(), request.activo());
        return rolUseCase.actualizar(id, rol);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> eliminar(@PathVariable UUID id) {
        return rolUseCase.eliminar(id);
    }
}
