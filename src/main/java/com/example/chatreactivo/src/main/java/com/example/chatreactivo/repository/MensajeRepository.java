package com.example.chatreactivo.repository;


import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.chatreactivo.model.Mensaje;

import reactor.core.publisher.Flux;

public interface MensajeRepository extends ReactiveCrudRepository<Mensaje, UUID> {

    @Query("""
        SELECT * FROM mensajes
        WHERE (remitente_id = :usuario1 AND receptor_id = :usuario2)
           OR (remitente_id = :usuario2 AND receptor_id = :usuario1)
        ORDER BY fecha_envio
    """)
    Flux<Mensaje> obtenerConversacion(UUID usuario1, UUID usuario2);
}