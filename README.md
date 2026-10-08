# RecetaYa - Microservicio de Dispensación

Microservicio desarrollado para el proyecto **RecetaYa** de la asignatura
**JVY0101 - Java: Diseño y Construcción de Soluciones Nativas en Nube**.

Su responsabilidad es gestionar y registrar la entrega de medicamentos
asociados a una receta.

## Tecnologías

- Java 21
- Spring Boot 4.1.1
- Maven
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Lombok
- Git / GitHub

## Arquitectura interna

El proyecto utiliza una arquitectura por capas:

```text
Cliente HTTP
    ↓
Controller
    ↓
Service
    ↓
Repository
    ↓
JPA / Hibernate
    ↓
MySQL
```

Estructura principal:

```text
src/main/java/com/dispensacion/
├── config/
├── controller/
├── dto/
├── entity/
├── repository/
├── service/
└── MsDispensacionApplication.java
```

### Responsabilidad de cada capa

- **Controller:** recibe las peticiones HTTP y entrega las respuestas.
- **DTO:** representa los datos recibidos desde la API.
- **Service:** contiene la lógica de negocio.
- **Repository:** gestiona el acceso a datos mediante Spring Data JPA.
- **Entity:** representa las tablas almacenadas en la base de datos.

## Modelo de datos

El microservicio utiliza dos entidades principales:

```text
DispensacionEntity
       1
       │
       │ OneToMany
       ▼
       N
DetalleDispensacionEntity
```

Una dispensación puede contener uno o varios medicamentos.

La relación inversa se implementa mediante `ManyToOne`.

## Requisitos previos

Para ejecutar el proyecto se requiere:

- Java 21
- MySQL
- Git

El proyecto incluye **Maven Wrapper**, por lo que no es obligatorio
tener Maven instalado globalmente.

## Base de datos

Crear una base de datos MySQL vacía:

```sql
CREATE DATABASE recetaya_dispensacion;
```

Las tablas son creadas automáticamente por Hibernate a partir de
las entidades JPA.

Tablas generadas:

```text
dispensaciones
detalle_dispensaciones
```

## Configuración

El archivo:

```text
src/main/resources/application.properties
```

utiliza la siguiente configuración local:

```properties
spring.application.name=ms-dispensacion

server.port=8084

spring.datasource.url=jdbc:mysql://localhost:3306/recetaya_dispensacion
spring.datasource.username=root
spring.datasource.password=

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Modificar usuario o contraseña si la configuración local de MySQL
es diferente.

## Clonar el repositorio

```bash
git clone https://github.com/PabloCanelos/recetaya-dispensacion.git
```

Ingresar al proyecto:

```bash
cd recetaya-dispensacion
```

## Compilar

En Windows:

```powershell
.\mvnw.cmd clean compile
```

Resultado esperado:

```text
BUILD SUCCESS
```

## Ejecutar desde Maven

```powershell
.\mvnw.cmd spring-boot:run
```

El microservicio queda disponible en:

```text
http://localhost:8084
```

## Generar el artefacto JAR

```powershell
.\mvnw.cmd clean package
```

El archivo generado queda en:

```text
target/ms-dispensacion-0.0.1-SNAPSHOT.jar
```

## Ejecutar el JAR

```powershell
java -jar target\ms-dispensacion-0.0.1-SNAPSHOT.jar
```

## API REST

Ruta base:

```text
http://localhost:8084/api/dispensaciones
```

| Método | Endpoint | Descripción | Respuesta |
|---|---|---|---|
| POST | `/api/dispensaciones` | Crear dispensación | 201 |
| GET | `/api/dispensaciones` | Listar dispensaciones | 200 |
| GET | `/api/dispensaciones/{id}` | Buscar por ID | 200 / 404 |
| GET | `/api/dispensaciones/receta/{idReceta}` | Buscar por receta | 200 |
| PUT | `/api/dispensaciones/{id}` | Actualizar dispensación | 200 / 404 |
| DELETE | `/api/dispensaciones/{id}` | Eliminar dispensación | 204 / 404 |

## Ejemplo POST

```http
POST /api/dispensaciones
Content-Type: application/json
```

Body:

```json
{
  "idReceta": 1,
  "idFarmaceutico": 1,
  "estado": "REGISTRADA",
  "detalles": [
    {
      "idMedicamento": 101,
      "cantidad": 2
    },
    {
      "idMedicamento": 205,
      "cantidad": 1
    }
  ]
}
```

Respuesta esperada:

```text
201 Created
```

El sistema genera automáticamente:

- `idDispensacion`
- `idDetalleDispensacion`
- `fechaDispensacion`

## Ejemplo GET

```http
GET /api/dispensaciones/1
```

Si existe:

```text
200 OK
```

Si no existe:

```text
404 Not Found
```

## Ejemplo PUT

```http
PUT /api/dispensaciones/1
Content-Type: application/json
```

```json
{
  "idReceta": 1,
  "idFarmaceutico": 2,
  "estado": "REGISTRADA",
  "detalles": [
    {
      "idMedicamento": 101,
      "cantidad": 3
    }
  ]
}
```

Respuesta esperada:

```text
200 OK
```

## Ejemplo DELETE

```http
DELETE /api/dispensaciones/1
```

Respuesta exitosa:

```text
204 No Content
```

Si el registro no existe:

```text
404 Not Found
```

## Persistencia

Las operaciones realizadas mediante la API son almacenadas en MySQL.

Hibernate administra la relación entre:

```text
dispensaciones
        ↓
detalle_dispensaciones
```

mediante las anotaciones JPA `@OneToMany` y `@ManyToOne`.

## Pruebas

Los endpoints pueden probarse utilizando:

- Bruno
- Postman
- Otra herramienta compatible con APIs REST

Se recomienda comprobar tanto casos exitosos como errores HTTP.

## Control de versiones

El repositorio utiliza la siguiente estrategia de ramas:

```text
main
  ↓
develop
  ↓
feature/*
```

Las funcionalidades son desarrolladas en ramas `feature/` y posteriormente
integradas a `develop` mediante Pull Request.

## Estado actual

Actualmente el microservicio permite:

- Registrar dispensaciones.
- Registrar múltiples medicamentos por dispensación.
- Consultar dispensaciones.
- Consultar por ID.
- Consultar por receta.
- Actualizar dispensaciones.
- Eliminar dispensaciones.
- Persistir información en MySQL.
- Generar y ejecutar un artefacto JAR.

La integración REST con los microservicios de Recetas e Inventario forma
parte de la integración global posterior de RecetaYa.