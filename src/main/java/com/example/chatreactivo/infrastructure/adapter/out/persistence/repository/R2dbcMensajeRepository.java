package com.example.chatreactivo.infrastructure.adapter.out.persistence.repository;

import com.example.chatreactivo.infrastructure.adapter.out.persistence.entity.MensajeEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface R2dbcMensajeRepository extends ReactiveCrudRepository<MensajeEntity, UUID> {

    @Query("""
        SELECT * FROM mensajes
        WHERE (remitente_id = :usuario1 AND receptor_id = :usuario2)
           OR (remitente_id = :usuario2 AND receptor_id = :usuario1)
        ORDER BY fecha_envio
    """)
    Flux<MensajeEntity> obtenerConversacion(UUID usuario1, UUID usuario2);
}
