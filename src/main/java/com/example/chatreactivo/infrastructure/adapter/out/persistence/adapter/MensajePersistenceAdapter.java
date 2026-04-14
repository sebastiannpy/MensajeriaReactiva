package com.example.chatreactivo.infrastructure.adapter.out.persistence.adapter;

import com.example.chatreactivo.domain.model.Mensaje;
import com.example.chatreactivo.domain.ports.out.MensajeRepositoryPort;
import com.example.chatreactivo.infrastructure.adapter.out.persistence.mapper.MensajePersistenceMapper;
import com.example.chatreactivo.infrastructure.adapter.out.persistence.repository.R2dbcMensajeRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class MensajePersistenceAdapter implements MensajeRepositoryPort {

    private final R2dbcMensajeRepository repository;
    private final MensajePersistenceMapper mapper;

    public MensajePersistenceAdapter(R2dbcMensajeRepository repository, MensajePersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<Mensaje> save(Mensaje mensaje) {
        return repository.save(mapper.toEntity(mensaje)).map(mapper::toDomain);
    }

    @Override
    public Flux<Mensaje> obtenerConversacion(UUID usuario1, UUID usuario2) {
        return repository.obtenerConversacion(usuario1, usuario2).map(mapper::toDomain);
    }
}
