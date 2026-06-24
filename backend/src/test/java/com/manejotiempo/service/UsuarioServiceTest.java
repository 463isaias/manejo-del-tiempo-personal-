package com.manejotiempo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.manejotiempo.model.Usuario;
import com.manejotiempo.repository.UsuarioRepository;

public class UsuarioServiceTest {

    @Test
    void testCreateAndFind() {
        UsuarioRepository repo = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repo);

        Usuario u = new Usuario();
        u.setUsuarioId(1L);
        u.setNombre("Isaias");
        u.setEmail("test@example.com");
        u.setPassword("123456");

        // Configurar el mock
        when(repo.save(any(Usuario.class))).thenReturn(u);
        when(repo.findById(1L)).thenReturn(Optional.of(u));

        // Crear
    
Usuario created = service.create(u);
assertNotNull(created);
assertEquals(1L, created.getUsuarioId());

        



        // Buscar
        Optional<Usuario> encontrado = service.findById(1);
        assertTrue(encontrado.isPresent());
        assertEquals("Isaias", encontrado.get().getNombre());
    }

    @Test
    void testUpdate() {
        UsuarioRepository repo = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repo);

        Usuario u = new Usuario();
        u.setUsuarioId(1L);
        u.setNombre("Isaias");

        when(repo.findById(1L)).thenReturn(Optional.of(u));
        when(repo.save(any(Usuario.class))).thenReturn(u);

        Usuario nuevo = new Usuario();
        nuevo.setNombre("Nuevo Nombre");

        Optional<Usuario> actualizado = service.update(1, nuevo);
        assertTrue(actualizado.isPresent());
        assertEquals("Nuevo Nombre", actualizado.get().getNombre());
    }

    @Test
    void testDelete() {
        UsuarioRepository repo = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repo);

        Usuario u = new Usuario();
        u.setUsuarioId(1L);
        u.setNombre("Isaias");

        when(repo.findById(1L)).thenReturn(Optional.of(u));

        boolean eliminado = service.delete(1);
        assertTrue(eliminado);

        // Verificar que se llamó a delete
        verify(repo, times(1)).delete(u);
    }

    @Test
    void testFindAll() {
        UsuarioRepository repo = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repo);

        when(repo.findAll()).thenReturn(Collections.emptyList());

        assertTrue(service.findAll().isEmpty());
    }
}
