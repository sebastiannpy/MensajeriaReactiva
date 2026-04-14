package com.example.chatreactivo.infrastructure.adapter.out.persistence.repository;

import com.example.chatreactivo.infrastructure.adapter.out.persistence.entity.RolEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface R2dbcRolRepository extends ReactiveCrudRepository<RolEntity, UUID> {
}
