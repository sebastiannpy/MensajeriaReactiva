package com.example.chatreactivo.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI chatOpenApi() {
        return new OpenAPI().info(new Info()
                .title("API Chat Reactivo")
                .version("1.0")
                .description("API reactiva con arquitectura hexagonal para usuarios, roles y mensajes"));
    }
}
