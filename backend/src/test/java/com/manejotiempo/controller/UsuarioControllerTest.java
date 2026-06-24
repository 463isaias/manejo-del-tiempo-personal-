package com.manejotiempo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manejotiempo.model.Usuario;
import com.manejotiempo.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // convierte objetos a JSON

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void limpiarBD() {
        usuarioRepository.deleteAll(); // limpia la tabla antes de cada test
    }

    @Test
    void testCrearUsuario() throws Exception {
        Usuario u = new Usuario();
        u.setNombre("Isaias");
        // Email dinámico para evitar duplicados
        u.setEmail("test" + System.currentTimeMillis() + "@example.com");
        u.setPassword("123456");

        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(u)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Isaias"))
                .andExpect(jsonPath("$.email").value(u.getEmail()));
    }

    @Test
    void testListarUsuarios() throws Exception {
        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testActualizarUsuario() throws Exception {
        // Crear usuario inicial
        Usuario u = new Usuario();
        u.setNombre("Isaias");
        u.setEmail("update" + System.currentTimeMillis() + "@example.com");
        u.setPassword("123456");
        Usuario guardado = usuarioRepository.save(u);

        // Nuevo objeto con datos actualizados
        Usuario actualizado = new Usuario();
        actualizado.setNombre("Nuevo Nombre");
        actualizado.setEmail(guardado.getEmail());
        actualizado.setPassword("654321");

        mockMvc.perform(put("/api/usuarios/" + guardado.getUsuarioId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Nuevo Nombre"));
    }

    @Test
    void testEliminarUsuario() throws Exception {
        // Crear usuario inicial
        Usuario u = new Usuario();
        u.setNombre("Isaias");
        u.setEmail("delete" + System.currentTimeMillis() + "@example.com");
        u.setPassword("123456");
        Usuario guardado = usuarioRepository.save(u);

        // Eliminar
        mockMvc.perform(delete("/api/usuarios/" + guardado.getUsuarioId()))
                .andExpect(status().isOk());
    }
}
