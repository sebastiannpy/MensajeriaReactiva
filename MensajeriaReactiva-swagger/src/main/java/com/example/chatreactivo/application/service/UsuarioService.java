package com.example.chatreactivo.application.service;

import com.example.chatreactivo.domain.model.Usuario;
import com.example.chatreactivo.domain.ports.in.UsuarioUseCase;
import com.example.chatreactivo.domain.ports.out.RolRepositoryPort;
import com.example.chatreactivo.domain.ports.out.UsuarioRepositoryPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UsuarioService implements UsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final RolRepositoryPort rolRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepositoryPort usuarioRepositoryPort,
            RolRepositoryPort rolRepositoryPort,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.rolRepositoryPort = rolRepositoryPort;
        this.passwordEncoder = passwordEncoder;
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

                    Usuario usuarioValidado = tuple.getT1();
                    String passwordEncriptada = passwordEncoder.encode(usuarioValidado.getPassword());
                    usuarioValidado.setPassword(passwordEncriptada);

                    return usuarioRepositoryPort.save(usuarioValidado);
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

                    String passwordEncriptada = passwordEncoder.encode(usuario.getPassword());
                    actual.setPassword(passwordEncriptada);

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