# Resumen del Proyecto Manejotiempo

## 1. Información General del Proyecto

### Descripción
Aplicación de manejo de tiempo personal desarrollada en **Java Spring Boot** con arquitectura REST y base de datos PostgreSQL.

### Stack Tecnológico
- **Lenguaje**: Java 21
- **Framework**: Spring Boot 3.2.5
- **Build**: Maven con Maven Wrapper (`mvnw.cmd`)
- **Empaquetado**: JAR
- **Base de datos**: PostgreSQL (configurada)
- **Template Engine**: Thymeleaf
- **Testing**: JUnit 5, Spring Boot Test, JaCoCo

### Ubicación
```
C:\Users\isaia\OneDrive\Escritorio\manejotiempo
├── backend/
├── frontend/
└── README.md
```

---

## 2. Configuración del Backend

### Puerto
- **Puerto actual**: 8082
- **Archivo de configuración**: `backend/src/main/resources/application.properties`

### Base de datos
- **URL**: `jdbc:postgresql://localhost:5432/manejo_tiempo`
- **Usuario**: `postgres`
- **Contraseña**: `1234`
- **Modo Hibernate**: `validate`

### URLs de acceso
- **Página inicial**: `http://localhost:8082/`
- **API Base**: `http://localhost:8082/api`

---

## 3. Estructura del Backend

### Capas de la aplicación
```
backend/src/main/java/com/manejotiempo/
├── ManejotiempoApplication.java (clase principal)
├── ApplicationStartupListener.java (listener de arranque)
├── controller/
│   ├── HomeController.java
│   ├── UsuarioController.java
│   ├── TareaController.java
│   ├── CategoriaController.java
│   ├── RegistroController.java
│   └── RecordatorioController.java
├── service/
│   ├── UsuarioService.java
│   ├── TareaService.java
│   ├── CategoriaService.java
│   ├── RegistroService.java
│   └── RecordatorioService.java
├── model/
│   ├── Usuario.java
│   ├── Tarea.java
│   ├── Categoria.java
│   ├── Meta.java
│   ├── Registro.java
│   ├── Recordatorio.java
│   ├── ClaseProgramada.java
│   └── TareaCategoria.java
├── repository/
│   └── (vacío - servicios actuales no usan JPA)
└── resources/
    ├── application.properties
    ├── templates/
    │   └── index.html
    └── static/
```

---

## 4. Entidades y Relaciones

### Entidades Disponibles
1. **Usuario**
2. **Tarea**
3. **Categoria**
4. **Meta**
5. **Registro**
6. **Recordatorio**
7. **ClaseProgramada**
8. **TareaCategoria** (tabla intermedia)

### Modelo de Relaciones

#### Usuario (usuarioId: Integer)
- Campos: `nombre`, `email`, `password`
- Relaciones:
  - `@OneToMany` → Meta
  - `@OneToMany` → Tarea
  - `@OneToMany` → ClaseProgramada
  - `@OneToMany` → Recordatorio

#### Tarea (tareaId: Integer)
- Campos: `nombre`, `descripcion`, `fechaInicio`, `fechaFin`, `estado`
- Relaciones:
  - `@ManyToOne` → Meta
  - `@ManyToOne` → Usuario
  - `@ManyToMany` → Categoria (tabla intermedia: tarea_categoria)
  - `@OneToMany` → Registro

#### Categoria (categoriaId: Integer)
- Campos: `nombre`
- Relaciones:
  - `@ManyToMany` (mappedBy) → Tarea

#### Meta (metaId: Integer)
- Campos: `titulo`, `descripcion`, `fechaObjetivo`
- Relaciones:
  - `@ManyToOne` → Usuario
  - `@OneToMany` → Tarea

#### Registro (id: Integer)
- Campos: `fecha`, `duracion`
- Relaciones:
  - `@ManyToOne` → Tarea

#### Recordatorio (id: Integer)
- Campos: `mensaje`, `fechaHora`, `tipo`
- Relaciones:
  - `@ManyToOne` → Usuario
  - `@ManyToOne` → Tarea
  - `@ManyToOne` → ClaseProgramada

#### ClaseProgramada (id: Integer)
- Campos: `titulo`, `profesor`, `fechaHora`, `duracion`, `ubicacion`
- Relaciones:
  - `@ManyToOne` → Usuario
  - `@OneToMany` → Recordatorio

#### TareaCategoria (id: Integer)
- Relaciones:
  - `@ManyToOne` → Tarea
  - `@ManyToOne` → Categoria

---

## 5. Endpoints REST

### Base URL
```
http://localhost:8082/api
```

### 5.1 Usuarios

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/usuarios` | Lista todos los usuarios |
| GET | `/api/usuarios/{id}` | Obtiene un usuario por ID |
| POST | `/api/usuarios` | Crea un usuario |
| PUT | `/api/usuarios/{id}` | Actualiza un usuario |
| DELETE | `/api/usuarios/{id}` | Elimina un usuario |

**Ejemplo de POST/PUT:**
```json
{
  "nombre": "Isaias",
  "email": "isa@example.com",
  "password": "1234"
}
```

### 5.2 Tareas

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/tareas` | Lista todas las tareas |
| GET | `/api/tareas/{id}` | Obtiene una tarea por ID |
| POST | `/api/tareas` | Crea una tarea |
| PUT | `/api/tareas/{id}` | Actualiza una tarea |
| DELETE | `/api/tareas/{id}` | Elimina una tarea |

**Ejemplo de POST/PUT:**
```json
{
  "nombre": "Estudiar",
  "descripcion": "Repasar Java",
  "fechaInicio": "2026-06-11",
  "fechaFin": "2026-06-12",
  "estado": "PENDIENTE"
}
```

### 5.3 Categorías

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/categorias` | Lista todas las categorías |
| GET | `/api/categorias/{id}` | Obtiene una categoría por ID |
| POST | `/api/categorias` | Crea una categoría |
| PUT | `/api/categorias/{id}` | Actualiza una categoría |
| DELETE | `/api/categorias/{id}` | Elimina una categoría |

**Ejemplo de POST/PUT:**
```json
{
  "nombre": "Trabajo"
}
```

### 5.4 Registros

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/registros` | Lista todos los registros |
| GET | `/api/registros/{id}` | Obtiene un registro por ID |
| POST | `/api/registros` | Crea un registro |
| PUT | `/api/registros/{id}` | Actualiza un registro |
| DELETE | `/api/registros/{id}` | Elimina un registro |

**Ejemplo de POST/PUT:**
```json
{
  "fecha": "2026-06-11",
  "duracion": 120
}
```

### 5.5 Recordatorios

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/recordatorios` | Lista todos los recordatorios |
| GET | `/api/recordatorios/{id}` | Obtiene un recordatorio por ID |
| POST | `/api/recordatorios` | Crea un recordatorio |
| PUT | `/api/recordatorios/{id}` | Actualiza un recordatorio |
| DELETE | `/api/recordatorios/{id}` | Elimina un recordatorio |

**Ejemplo de POST/PUT:**
```json
{
  "mensaje": "Reunión",
  "fechaHora": "2026-06-12T09:00:00",
  "tipo": "EMAIL"
}
```

### 5.6 Página Inicial

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/` | Sirve la plantilla HTML de bienvenida |
| GET | `/api` | Redirige a `/` |

---

## 6. Servicios

### Patrón CRUD en Memoria

Todos los servicios siguen el mismo patrón:
```java
private final Map<Integer, Entidad> store = new ConcurrentHashMap<>();
private final AtomicInteger idGen = new AtomicInteger(1);

public List<Entidad> findAll() { ... }
public Optional<Entidad> findById(Integer id) { ... }
public Entidad create(Entidad e) { ... }
public Optional<Entidad> update(Integer id, Entidad e) { ... }
public boolean delete(Integer id) { ... }
```

### Comportamiento
- Almacenamiento temporal en memoria
- Thread-safe gracias a `ConcurrentHashMap` y `AtomicInteger`
- Generación automática de IDs
- No persiste en PostgreSQL (aún)

---

## 7. Pruebas Unitarias

### Ubicación
```
backend/src/test/java/com/manejotiempo/
├── ManejotiempoApplicationTests.java
└── service/
    ├── UsuarioServiceTest.java
    ├── TareaServiceTest.java
    ├── CategoriaServiceTest.java
    ├── RegistroServiceTest.java
    └── RecordatorioServiceTest.java
```

### Cobertura de Pruebas

#### UsuarioServiceTest
- `createAndFind()`: Crea un usuario y lo busca
- `updateDelete()`: Actualiza y elimina un usuario
- `listEmpty()`: Verifica que la lista de usuarios es válida
- `updateNonExisting()`: Intenta actualizar/eliminar un usuario que no existe

#### TareaServiceTest
- `createAndGet()`: Crea una tarea y la obtiene
- `updateAndDelete()`: Actualiza y elimina una tarea
- `list()`: Lista todas las tareas
- `updateNotFoundAndFindById()`: Verifica casos de no encontrado

#### CategoriaServiceTest
- `createGetUpdateDelete()`: CRUD completo
- `list()`: Lista todas las categorías
- `findByIdAndNotFoundCases()`: Casos de no encontrado

#### RegistroServiceTest
- `crud()`: Operaciones CRUD completas
- `list()`: Lista todos los registros
- `findByIdAndNotFound()`: Búsqueda y casos no encontrados

#### RecordatorioServiceTest
- `crud()`: Operaciones CRUD
- `list()`: Lista todos
- `findByIdAndNotFound()`: Búsqueda

#### ManejotiempoApplicationTests
- `contextLoads()`: Verifica que el contexto Spring Boot se carga correctamente

### Comportamiento de las Pruebas
- **Tipo**: Pruebas unitarias puras (sin Spring, sin BD)
- **Dependencias**: JUnit 5
- **Alcance**: Servicios y lógica en memoria
- **NO CUBREN**:
  - Controladores REST
  - Persistencia en PostgreSQL
  - Endpoints HTTP
  - Integración

### Ejecución de Pruebas
```powershell
cd backend
.\mvnw.cmd test
```

**Resultados**: se generan en `target/surefire-reports/`

---

## 8. Cambios Realizados en Esta Sesión

### 1. Puerto del Backend
- **Cambio**: 8081 → 8082
- **Archivo**: `backend/src/main/resources/application.properties`
- **Línea**: `server.port=8082`
- **Razón**: Evitar conflictos con otra aplicación

### 2. Controlador de Inicio
- **Archivo creado**: `HomeController.java`
- **Rutas**:
  - `GET /` → sirve `index.html`
  - `GET /api` → redirige a `/`

### 3. Plantilla HTML
- **Archivo creado**: `templates/index.html`
- **Contenido**: Dashboard de bienvenida con cards e información
- **Estilos**: CSS integrado con gradientes y animaciones

### 4. Listener de Arranque
- **Archivo creado**: `ApplicationStartupListener.java`
- **Función**: Imprime en la terminal un mensaje con los enlaces al arrancar
- **Enlace mostrado**:
  ```
  ╔═══════════════════════════════════════════════════════════╗
  ║        🎉 Aplicación iniciada correctamente 🎉            ║
  ║   Accede a la aplicación en:                              ║
  ║   👉 http://localhost:8082                                ║
  ║   API REST:                                               ║
  ║   👉 http://localhost:8082/api                            ║
  ╚═══════════════════════════════════════════════════════════╝
  ```

### 5. Dependencia Thymeleaf
- **Agregada**: `spring-boot-starter-thymeleaf` en `pom.xml`
- **Razón**: Para servir plantillas HTML desde el backend

---

## 9. Comandos Importantes

### Compilar y empaquetar
```powershell
cd backend
.\mvnw.cmd clean package -DskipTests
```

### Arrancar con Maven
```powershell
cd backend
.\mvnw.cmd spring-boot:run
```

### Ejecutar JAR generado
```powershell
cd backend
java -jar target\manejotiempo-0.0.1-SNAPSHOT.jar
```

### Ejecutar pruebas
```powershell
cd backend
.\mvnw.cmd test
```

### Limpiar compilación anterior
```powershell
cd backend
.\mvnw.cmd clean
```

---

## 10. Estado Actual del Proyecto

### Completado
✅ Arquitectura REST básica con 5 servicios/controladores
✅ Modelos JPA definidos (8 entidades)
✅ Pruebas unitarias para servicios
✅ Configuración de PostgreSQL
✅ Página inicial con plantilla Thymeleaf
✅ Maven con empaquetamiento JAR
✅ Listener que muestra enlace al arrancar

### Pendiente
❌ Integración real con PostgreSQL (repositorios JPA)
❌ Controladores para Meta y ClaseProgramada
❌ Pruebas de integración con MockMvc
❌ Pruebas de endpoints HTTP
❌ Frontend completo (solo existe index.html)
❌ Autenticación/Seguridad

---

## 11. Cómo Usar el Proyecto

### Paso 1: Iniciar el backend
```powershell
cd c:\Users\isaia\OneDrive\Escritorio\manejotiempo\backend
.\mvnw.cmd spring-boot:run
```

### Paso 2: Acceder a la aplicación
Abre en el navegador:
```
http://localhost:8082
```

### Paso 3: Probar endpoints REST
Usa **Postman**, **Thunder Client** o **curl**:

```bash
# Listar usuarios
curl http://localhost:8082/api/usuarios

# Crear usuario
curl -X POST http://localhost:8082/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Isaias","email":"isa@example.com","password":"1234"}'
```

---

## 12. Dependencias Maven

```xml
<!-- Web -->
<spring-boot-starter-web>

<!-- JPA/Hibernate -->
<spring-boot-starter-data-jpa>

<!-- PostgreSQL -->
<postgresql>

<!-- Template Engine -->
<spring-boot-starter-thymeleaf>

<!-- Testing -->
<spring-boot-starter-test>

<!-- Coverage -->
<jacoco-maven-plugin>
```

---

## 13. Información Técnica Adicional

### Versiones
- Java: 21
- Spring Boot: 3.2.5
- Maven: con wrapper
- JUnit: 5

### Plugins Maven
- `maven-compiler-plugin` v3.11.0
- `maven-surefire-plugin` v3.0.0
- `jacoco-maven-plugin` v0.8.11

### Persistencia Actual
- **Estado**: Mock en memoria
- **Estructura de datos**: `ConcurrentHashMap<Integer, Entidad>`
- **Generador de IDs**: `AtomicInteger`

---

## Conclusión

El proyecto **manejotiempo** es una aplicación Spring Boot con arquitectura REST bien estructurada, modelos de datos completos y pruebas unitarias. Actualmente los servicios usan almacenamiento en memoria, pero la base de datos PostgreSQL está configurada para futuras integraciones con repositorios JPA.

El backend está funcional en el puerto **8082** y expone **5 endpoints principales** (Usuarios, Tareas, Categorías, Registros, Recordatorios) con operaciones CRUD completas.
