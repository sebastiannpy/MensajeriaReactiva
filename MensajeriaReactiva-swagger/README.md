# Chat Reactivo Hexagonal

Proyecto backend en Spring Boot WebFlux con arquitectura hexagonal.

## Requisitos
- Java 21
- PostgreSQL
- Maven

## Base de datos esperada
Tablas:
- roles
- usuarios
- mensajes

## Swagger
- http://localhost:8081/swagger-ui.html

## Endpoints principales
- POST /api/auth/login
- POST /api/usuarios
- PUT /api/usuarios/{id}
- PATCH /api/usuarios/{id}/inactivar
- CRUD /api/roles
- POST /api/mensajes
- GET /api/mensajes?usuario1=...&usuario2=...
- GET /api/mensajes/stream/{usuarioId}
