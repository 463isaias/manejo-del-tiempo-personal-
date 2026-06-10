# Manejotiempo

Backend now targets Java 21 (latest LTS).

Prerequisites
- JDK 21 installed and JAVA_HOME set.
- Maven wrapper is included in the `backend` folder.

Build (from repository root):

```bash
cd backend
./mvnw -DskipTests package   # On Windows: .\mvnw.cmd -DskipTests package
```

Notes
- The backend `pom.xml` property `java.version` has been updated to 21 and the Maven Compiler plugin configured to use `${java.version}`.
- See `backend/pom.xml` for the change.
