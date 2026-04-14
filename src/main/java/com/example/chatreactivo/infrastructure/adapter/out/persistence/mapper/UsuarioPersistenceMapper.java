package com.example.chatreactivo.infrastructure.adapter.out.persistence.mapper;

import com.example.chatreactivo.domain.model.Usuario;
import com.example.chatreactivo.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioPersistenceMapper {
    public Usuario toDomain(UsuarioEntity entity) {
        return new Usuario(entity.getId(), entity.getUsername(), entity.getPassword(), entity.getNombre(), entity.getCorreo(), entity.getActivo(), entity.getRolId());
    }

    public UsuarioEntity toEntity(Usuario domain) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(domain.getId());
        entity.setUsername(domain.getUsername());
        entity.setPassword(domain.getPassword());
        entity.setNombre(domain.getNombre());
        entity.setCorreo(domain.getCorreo());
        entity.setActivo(domain.getActivo());
        entity.setRolId(domain.getRolId());
        return entity;
    }
}
