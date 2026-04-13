package com.example.chatreactivo.infrastructure.adapter.out.persistence.mapper;

import com.example.chatreactivo.domain.model.Mensaje;
import com.example.chatreactivo.infrastructure.adapter.out.persistence.entity.MensajeEntity;
import org.springframework.stereotype.Component;

@Component
public class MensajePersistenceMapper {
    public Mensaje toDomain(MensajeEntity entity) {
        return new Mensaje(entity.getId(), entity.getRemitenteId(), entity.getReceptorId(), entity.getContenido(), entity.getFechaEnvio());
    }

    public MensajeEntity toEntity(Mensaje domain) {
        MensajeEntity entity = new MensajeEntity();
        entity.setId(domain.getId());
        entity.setRemitenteId(domain.getRemitenteId());
        entity.setReceptorId(domain.getReceptorId());
        entity.setContenido(domain.getContenido());
        entity.setFechaEnvio(domain.getFechaEnvio());
        return entity;
    }
}
