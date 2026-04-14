package com.example.chatreactivo.application.service;

import com.example.chatreactivo.domain.model.Usuario;
import com.example.chatreactivo.domain.ports.in.UsuarioUseCase;
import com.example.chatreactivo.domain.ports.out.RolRepositoryPort;
import com.example.chatreactivo.domain.ports.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UsuarioService implements UsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final RolRepositoryPort rolRepositoryPort;

    public UsuarioService(UsuarioRepositoryPort usuarioRepositoryPort, RolRepositoryPort rolRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.rolRepositoryPort = rolRepositoryPort;
    }

    @Override
    public Mono<Usuario> registrar(Usuario usuario) {
        usuario.setActivo(usuario.getActivo() == null ? Boolean.TRUE : usuario.getActivo());

        Mono<Usuario> validarUsername = usuarioRepositoryPort.findByUsername(usuario.getUsername())
                .flatMap(existente -> Mono.<Usuario>error(new RuntimeException("El username ya existe")))
                .switchIfEmpty(Mono.just(usuario));

        Mono<Boolean> validarRol = usuario.getRolId() == null
                ? Mono.just(true)
                : rolRepositoryPort.existsById(usuario.getRolId());

        return Mono.zip(validarUsername, validarRol)
                .flatMap(tuple -> {
                    if (!tuple.getT2()) {
                        return Mono.error(new RuntimeException("El rol no existe"));
                    }
                    return usuarioRepositoryPort.save(tuple.getT1());
                });
    }

    @Override
    public Flux<Usuario> listar() {
        return usuarioRepositoryPort.findAll();
    }

    @Override
    public Mono<Usuario> obtenerPorId(UUID id) {
        return usuarioRepositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Usuario no encontrado")));
    }

    @Override
    public Mono<Usuario> actualizar(UUID id, Usuario usuario) {
        return usuarioRepositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Usuario no encontrado")))
                .flatMap(actual -> {
                    actual.setNombre(usuario.getNombre());
                    actual.setCorreo(usuario.getCorreo());
                    actual.setPassword(usuario.getPassword());
                    actual.setRolId(usuario.getRolId());
                    return usuarioRepositoryPort.save(actual);
                });
    }

    @Override
    public Mono<Usuario> inactivar(UUID id) {
        return usuarioRepositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Usuario no encontrado")))
                .map(usuario -> {
                    usuario.setActivo(Boolean.FALSE);
                    return usuario;
                })
                .flatMap(usuarioRepositoryPort::save);
    }
}
