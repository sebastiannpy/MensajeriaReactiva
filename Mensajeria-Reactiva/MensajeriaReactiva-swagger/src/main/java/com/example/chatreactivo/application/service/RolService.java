package com.example.chatreactivo.application.service;

import com.example.chatreactivo.domain.model.Rol;
import com.example.chatreactivo.domain.ports.in.RolUseCase;
import com.example.chatreactivo.domain.ports.out.RolRepositoryPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class RolService implements RolUseCase {

    private final RolRepositoryPort rolRepositoryPort;

    public RolService(RolRepositoryPort rolRepositoryPort) {
        this.rolRepositoryPort = rolRepositoryPort;
    }

    @Override
    public Mono<Rol> crear(Rol rol) {
        rol.setActivo(rol.getActivo() == null ? Boolean.TRUE : rol.getActivo());
        return rolRepositoryPort.save(rol);
    }

    @Override
    public Flux<Rol> listar() {
        return rolRepositoryPort.findAll();
    }

    @Override
    public Mono<Rol> obtenerPorId(UUID id) {
        return rolRepositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Rol no encontrado")));
    }

    @Override
    public Mono<Rol> actualizar(UUID id, Rol rol) {
        return rolRepositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Rol no encontrado")))
                .flatMap(actual -> {
                    actual.setNombre(rol.getNombre());
                    actual.setDescripcion(rol.getDescripcion());
                    actual.setActivo(rol.getActivo());
                    return rolRepositoryPort.save(actual);
                });
    }

    @Override
    public Mono<Void> eliminar(UUID id) {
        return rolRepositoryPort.existsById(id)
                .filter(Boolean::booleanValue)
                .switchIfEmpty(Mono.error(new RuntimeException("Rol no encontrado")))
                .flatMap(existe -> rolRepositoryPort.deleteById(id));
    }
}
