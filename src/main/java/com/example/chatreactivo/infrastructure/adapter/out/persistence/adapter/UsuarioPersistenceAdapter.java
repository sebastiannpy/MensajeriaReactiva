package com.example.chatreactivo.infrastructure.adapter.out.persistence.adapter;

import com.example.chatreactivo.domain.model.Usuario;
import com.example.chatreactivo.domain.ports.out.UsuarioRepositoryPort;
import com.example.chatreactivo.infrastructure.adapter.out.persistence.mapper.UsuarioPersistenceMapper;
import com.example.chatreactivo.infrastructure.adapter.out.persistence.repository.R2dbcUsuarioRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class UsuarioPersistenceAdapter implements UsuarioRepositoryPort {

    private final R2dbcUsuarioRepository repository;
    private final UsuarioPersistenceMapper mapper;

    public UsuarioPersistenceAdapter(R2dbcUsuarioRepository repository, UsuarioPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<Usuario> save(Usuario usuario) {
        return repository.save(mapper.toEntity(usuario)).map(mapper::toDomain);
    }

    @Override
    public Flux<Usuario> findAll() {
        return repository.findAll().map(mapper::toDomain);
    }

    @Override
    public Mono<Usuario> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Mono<Usuario> findByUsername(String username) {
        return repository.findByUsername(username).map(mapper::toDomain);
    }
}
