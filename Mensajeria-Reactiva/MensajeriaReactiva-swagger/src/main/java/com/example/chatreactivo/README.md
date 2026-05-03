# Chat reactivo básico

Proyecto mínimo con:
- Java 21
- Maven
- Spring WebFlux
- PostgreSQL con R2DBC

## Base de datos usada
Tablas:
- usuarios(id, username)
- mensajes(id, remitente_id, receptor_id, contenido, fecha_envio)

## Configuración
Editar `src/main/resources/application.yaml` con:
- nombre de la base
- usuario de PostgreSQL
- contraseña

## Endpoints
- `GET /api/usuarios`
- `POST /api/usuarios`
- `POST /api/mensajes`
- `GET /api/mensajes?usuario1={id1}&usuario2={id2}`
- `GET /api/mensajes/stream/{usuarioId}`

## Probar
1. Ejecutar la app.
2. Abrir `http://localhost:8080`.
3. Cargar usuarios.
4. Abrir chat.
5. Enviar mensajes.
