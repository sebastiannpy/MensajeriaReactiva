# Proyecto Integrador - Mensajería Reactiva

## Curso
Programación Reactiva con Spring WebFlux

## Programa
Ingeniería de Sistemas / Desarrollo de Software

## Nivel
Intermedio - Avanzado

## Modalidad
Desarrollo Backend Reactivo

---

# 1. Descripción General

El presente proyecto integrador tiene como finalidad aplicar los fundamentos de la programación reactiva y la arquitectura hexagonal, también conocida como Ports & Adapters, mediante el desarrollo de una API REST no bloqueante orientada a la gestión de un sistema de mensajería entre usuarios.

El sistema permite administrar usuarios, roles y mensajes, utilizando Spring Boot WebFlux para la construcción de servicios reactivos y R2DBC para la persistencia no bloqueante en una base de datos PostgreSQL. Además, el proyecto busca separar claramente las responsabilidades entre las capas de dominio, aplicación e infraestructura, favoreciendo un diseño mantenible, escalable y alineado con buenas prácticas de desarrollo backend.

---

# 2. Objetivo del Proyecto

Diseñar e implementar un sistema backend reactivo para la gestión de mensajería entre usuarios, garantizando el uso adecuado de flujos reactivos, separación de responsabilidades mediante arquitectura hexagonal y persistencia no bloqueante con PostgreSQL.

El proyecto permite registrar usuarios, gestionar roles, autenticar usuarios, enviar mensajes entre usuarios y consultar mensajes de manera reactiva, aplicando conceptos propios de la programación reactiva como `Mono`, `Flux` y operadores funcionales de transformación y composición de datos.

---

# 3. Alcance Funcional

El sistema contempla las siguientes funcionalidades principales:

- Registro de usuarios en el sistema.
- Consulta de usuarios registrados.
- Actualización de información de usuarios.
- Inactivación de usuarios.
- Creación y consulta de roles.
- Asociación de roles a usuarios.
- Autenticación básica de usuarios mediante nombre de usuario y contraseña.
- Envío de mensajes entre usuarios registrados.
- Consulta de mensajes almacenados.
- Implementación de flujos reactivos para el procesamiento de usuarios, roles y mensajes.
- Exposición de endpoints REST no bloqueantes.
- Documentación y prueba de endpoints mediante Swagger.
- Implementación de seguridad básica mediante cifrado/hash de contraseñas con BCrypt.
- Preparación del sistema para futuras mejoras de autenticación con JWT.

---

# 4. Requisitos Técnicos Obligatorios

El proyecto debe cumplir con los siguientes requisitos técnicos:

- Uso obligatorio de programación reactiva con Spring WebFlux.
- Uso de `Mono` para operaciones que retornan cero o un elemento.
- Uso de `Flux` para operaciones que retornan múltiples elementos.
- Aplicación de operadores reactivos como:
  - `map`
  - `flatMap`
  - `zip`
  - `switchIfEmpty`
  - `filter`
  - `collectList`
  - `onErrorResume`
- Prohibido el uso de `.block()` dentro de la lógica del sistema.
- Implementación de arquitectura hexagonal, separando:
  - Dominio
  - Casos de uso
  - Puertos de entrada
  - Puertos de salida
  - Adaptadores de infraestructura
  - Controladores REST
- Persistencia reactiva mediante Spring Data R2DBC.
- Uso de PostgreSQL como sistema gestor de base de datos.
- Implementación de repositorios reactivos para las entidades principales del sistema.
- Manejo de entidades principales:
  - Usuario
  - Rol
  - Mensaje
- Exposición de endpoints REST mediante controladores reactivos.
- Documentación de endpoints mediante Swagger/OpenAPI.
- Implementación de seguridad en contraseñas mediante BCrypt.
- Evitar lógica de negocio dentro de los controladores.
- Centralizar la lógica de negocio en la capa de servicios o casos de uso.
- Mantener una estructura de proyecto organizada por capas.
