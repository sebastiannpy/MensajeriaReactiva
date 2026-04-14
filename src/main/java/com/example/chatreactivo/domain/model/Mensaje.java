package com.example.chatreactivo.domain.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public class Mensaje {
    private UUID id;
    private UUID remitenteId;
    private UUID receptorId;
    private String contenido;
    private OffsetDateTime fechaEnvio;

    public Mensaje() {}

    public Mensaje(UUID id, UUID remitenteId, UUID receptorId, String contenido, OffsetDateTime fechaEnvio) {
        this.id = id;
        this.remitenteId = remitenteId;
        this.receptorId = receptorId;
        this.contenido = contenido;
        this.fechaEnvio = fechaEnvio;
    }

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
