# Backend — Pruebas y cobertura

Instrucciones rápidas:

- Ejecutar tests y generar informe JaCoCo:

```bash
mvn -f backend/pom.xml test
```

- Informe de cobertura HTML:

Open `backend/target/site/jacoco/index.html` in your browser.

¿Qué incluye este branch?

- Servicios en `src/main/java/com/manejotiempo/service/`
- Controladores en `src/main/java/com/manejotiempo/controller/`
- Tests de la capa de servicios en `src/test/java/com/manejotiempo/service/`
- JaCoCo añadido al `pom.xml` para generar reportes de cobertura
