package com.example.chatreactivo.infrastructure.adapter.out.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

@Table("mensajes")
public class MensajeEntity {
    @Id
    private UUID id;
    @Column("remitente_id")
    private UUID remitenteId;
    @Column("receptor_id")
    private UUID receptorId;
    private String contenido;
    @Column("fecha_envio")
    private OffsetDateTime fechaEnvio;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getRemitenteId() { return remitenteId; }
    public void setRemitenteId(UUID remitenteId) { this.remitenteId = remitenteId; }
    public UUID getReceptorId() { return receptorId; }
    public void setReceptorId(UUID receptorId) { this.receptorId = receptorId; }
    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
    public OffsetDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(OffsetDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }
}
