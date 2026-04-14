package com.example.chatreactivo.infrastructure.adapter.out.persistence.repository;

import com.example.chatreactivo.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface R2dbcUsuarioRepository extends ReactiveCrudRepository<UsuarioEntity, UUID> {
    Mono<UsuarioEntity> findByUsername(String username);
}
