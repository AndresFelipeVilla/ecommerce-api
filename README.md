# Spring Boot Template

Spring Boot 4 · Java 17 · PostgreSQL · JWT · Spring Modulith

## Quickstart

### 1. Requisitos

- Docker Desktop
- Java 17 (para desarrollo sin Docker)
- Maven 3.9+

### 2. Configurar variables de entorno

```bash
cp .env.example .env
```

Edita `.env` con tus valores. **Nunca commitees `.env`.**

Variables obligatorias:

| Variable | Descripción |
|---|---|
| `POSTGRES_DB` | Nombre de la base de datos |
| `POSTGRES_USER` | Usuario de PostgreSQL |
| `POSTGRES_PASSWORD` | Contraseña de PostgreSQL |
| `DATABASE_URL` | JDBC URL completa |
| `DATABASE_USERNAME` | Usuario para la app |
| `DATABASE_PASSWORD` | Contraseña para la app |
| `JWT_SECRET` | Secreto JWT — mínimo 32 caracteres aleatorios |
| `JWT_EXPIRATION` | Expiración del token en ms (ej: `86400000` = 1 día) |

Generar un `JWT_SECRET` seguro:
```bash
openssl rand -hex 32
```

### 3. Levantar el stack de desarrollo

```bash
docker-compose up --build
```

| Servicio | URL |
|---|---|
| API | http://localhost:8080 |
| Debug JDWP | localhost:5005 |
| PostgreSQL | localhost:5432 |

### 4. Endpoints disponibles

```
POST /api/auth/register   # Registrar usuario
POST /api/auth/login      # Iniciar sesión → retorna JWT
GET  /actuator/health     # Estado de la app (público)
```

### 5. Perfiles de Spring

| Perfil | Activación | Uso |
|---|---|---|
| `dev` | Por defecto | Docker local, logging DEBUG |
| `prod` | `SPRING_PROFILES_ACTIVE=prod` | Producción, logging WARN |

---

## Comandos Docker

```bash
# Desarrollo
docker-compose up --build
docker-compose down

# Producción
docker build --target production -t app:prod .
docker run --env-file .env.prod -p 8080:8080 app:prod

# Build solo una etapa
docker build --target development -t app:dev .
docker build --target builder    -t app:builder .
```

## Seguridad

- Los secretos se gestionan **solo** via variables de entorno
- JWT sin valor por defecto en producción — el arranque falla si no está seteado
- HTTP Security Headers activos: `HSTS`, `X-Frame-Options: DENY`, `CSP`, `Referrer-Policy`
- CORS configurable por entorno via `app.cors.allowed-origins`
- BCrypt para contraseñas
- Sesiones stateless (JWT)
- Usuario no-root en contenedor de producción
