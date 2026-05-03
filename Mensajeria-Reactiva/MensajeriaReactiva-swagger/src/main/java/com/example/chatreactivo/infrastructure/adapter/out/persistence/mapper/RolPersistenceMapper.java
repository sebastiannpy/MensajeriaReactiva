package com.example.chatreactivo.infrastructure.adapter.out.persistence.mapper;

import com.example.chatreactivo.domain.model.Rol;
import com.example.chatreactivo.infrastructure.adapter.out.persistence.entity.RolEntity;
import org.springframework.stereotype.Component;

@Component
public class RolPersistenceMapper {
    public Rol toDomain(RolEntity entity) {
        return new Rol(entity.getId(), entity.getNombre(), entity.getDescripcion(), entity.getActivo());
    }

    public RolEntity toEntity(Rol domain) {
        RolEntity entity = new RolEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setDescripcion(domain.getDescripcion());
        entity.setActivo(domain.getActivo());
        return entity;
    }
}
