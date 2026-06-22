# Frontend - Manejo de Tiempo

Este frontend es una aplicación React creada con Vite para consumir el backend de `manejotiempo`.

## Requisitos

- Node.js 18+ instalado
- Backend corriendo en `http://localhost:8081`

## Instalación

Desde la carpeta `frontend`:

```bash
npm install
```

## Ejecución local

Arranca el servidor de desarrollo:

```bash
npm run dev -- --host 0.0.0.0
```

Luego abre la aplicación en:

```text
http://localhost:5174/
```

## Detalles

- El backend está configurado en `http://localhost:8081/api`
- Todo el frontend está en `frontend/src`
- Los componentes principales se encuentran en `frontend/src/App.jsx`
- Estilos principales en `frontend/src/styles.css`

## Notas

- La aplicación es responsive y atiende secciones de `Usuarios`, `Tareas`, `Categorías`, `Registros` y `Recordatorios`.
- Si el puerto `5173` está ocupado, Vite intentará otro puerto disponible automáticamente.
