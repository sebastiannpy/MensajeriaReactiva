package com.example.chatreactivo.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Usuario {
    private UUID id;
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    
    private String nombre;
    private String correo;
    private Boolean activo;
    private UUID rolId;

    public Usuario() {}

    public Usuario(UUID id, String username, String password, String nombre, String correo, Boolean activo, UUID rolId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.correo = correo;
        this.activo = activo;
        this.rolId = rolId;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    public UUID getRolId() { return rolId; }
    public void setRolId(UUID rolId) { this.rolId = rolId; }
}
