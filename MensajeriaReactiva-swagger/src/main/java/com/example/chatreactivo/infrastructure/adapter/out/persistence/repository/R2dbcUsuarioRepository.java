package com.example.chatreactivo.infrastructure.adapter.out.persistence.repository;

import com.example.chatreactivo.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface R2dbcUsuarioRepository extends ReactiveCrudRepository<UsuarioEntity, UUID> {

    @Query("""
            SELECT id, username, password, nombre, correo, activo, rol_id
            FROM usuarios
            WHERE username = :username
            LIMIT 1
            """)
    Mono<UsuarioEntity> findByUsername(String username);
}