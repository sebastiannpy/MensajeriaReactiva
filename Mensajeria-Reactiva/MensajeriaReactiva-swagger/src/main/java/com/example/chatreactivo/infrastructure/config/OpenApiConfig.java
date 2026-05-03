package com.example.chatreactivo.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI chatOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Chat Reactivo - Arquitectura Hexagonal")
                        .version("1.0.0")
                        .description("""
                                API REST reactiva construida con **Spring WebFlux** y **arquitectura hexagonal**.
                                
                                ## Características
                                - Programación reactiva con Project Reactor (Mono/Flux)
                                - Persistencia reactiva con R2DBC + PostgreSQL
                                - Mensajería en tiempo real mediante **Server-Sent Events (SSE)**
                                - Arquitectura Hexagonal (Ports & Adapters)
                                
                                ## Módulos
                                - **Autenticación**: Login de usuarios
                                - **Usuarios**: CRUD completo con baja lógica
                                - **Roles**: Gestión de roles del sistema
                                - **Mensajes**: Envío, consulta e streaming en tiempo real
                                """)
                        .contact(new Contact()
                                .name("Chat Reactivo")
                                .email("soporte@chatreactivo.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server().url("http://localhost:8081").description("Servidor local de desarrollo")))
                .tags(List.of(
                        new Tag().name("Autenticación").description("Endpoints para autenticación de usuarios"),
                        new Tag().name("Usuarios").description("Gestión de usuarios del sistema de mensajería"),
                        new Tag().name("Roles").description("Gestión de roles de usuario"),
                        new Tag().name("Mensajes").description("Envío y consulta de mensajes entre usuarios. Incluye soporte reactivo con SSE")
                ));
    }
}
