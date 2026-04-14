package com.example.chatreactivo.infrastructure.adapter.out.persistence.adapter;

import com.example.chatreactivo.domain.model.Rol;
import com.example.chatreactivo.domain.ports.out.RolRepositoryPort;
import com.example.chatreactivo.infrastructure.adapter.out.persistence.mapper.RolPersistenceMapper;
import com.example.chatreactivo.infrastructure.adapter.out.persistence.repository.R2dbcRolRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class RolPersistenceAdapter implements RolRepositoryPort {

    private final R2dbcRolRepository repository;
    private final RolPersistenceMapper mapper;

    public RolPersistenceAdapter(R2dbcRolRepository repository, RolPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<Rol> save(Rol rol) {
        return repository.save(mapper.toEntity(rol)).map(mapper::toDomain);
    }

    @Override
    public Flux<Rol> findAll() {
        return repository.findAll().map(mapper::toDomain);
    }

    @Override
    public Mono<Rol> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Boolean> existsById(UUID id) {
        return repository.existsById(id);
    }
}
