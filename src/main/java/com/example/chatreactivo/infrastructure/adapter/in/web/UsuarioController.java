package com.example.chatreactivo.infrastructure.adapter.in.web;

import com.example.chatreactivo.domain.model.Usuario;
import com.example.chatreactivo.domain.ports.in.UsuarioUseCase;
import com.example.chatreactivo.infrastructure.adapter.in.web.dto.ActualizarUsuarioRequest;
import com.example.chatreactivo.infrastructure.adapter.in.web.dto.CrearUsuarioRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioUseCase usuarioUseCase;

    public UsuarioController(UsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }

    @PostMapping
    public Mono<Usuario> registrar(@Valid @RequestBody CrearUsuarioRequest request) {
        Usuario usuario = new Usuario(null, request.username(), request.password(), request.nombre(), request.correo(), true, request.rolId());
        return usuarioUseCase.registrar(usuario);
    }

    @GetMapping
    public Flux<Usuario> listar() {
        return usuarioUseCase.listar();
    }

    @GetMapping("/{id}")
    public Mono<Usuario> obtenerPorId(@PathVariable UUID id) {
        return usuarioUseCase.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Mono<Usuario> actualizar(@PathVariable UUID id, @Valid @RequestBody ActualizarUsuarioRequest request) {
        Usuario usuario = new Usuario(id, null, request.password(), request.nombre(), request.correo(), true, request.rolId());
        return usuarioUseCase.actualizar(id, usuario);
    }

    @PatchMapping("/{id}/inactivar")
    public Mono<Usuario> inactivar(@PathVariable UUID id) {
        return usuarioUseCase.inactivar(id);
    }
}
