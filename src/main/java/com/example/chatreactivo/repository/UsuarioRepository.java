package com.example.chatreactivo.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.chatreactivo.model.Usuario;

import reactor.core.publisher.Mono;

public interface UsuarioRepository extends ReactiveCrudRepository<Usuario, UUID> {
    Mono<Usuario> findByUsername(String username);
}