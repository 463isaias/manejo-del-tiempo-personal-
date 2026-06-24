package com.manejotiempo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.manejotiempo.model.Usuario;
import com.manejotiempo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(Long.valueOf(id));
    }

    public Usuario create(Usuario u) {
        return usuarioRepository.save(u);
    }

    public Optional<Usuario> update(Integer id, Usuario u) {
        return usuarioRepository.findById(Long.valueOf(id)).map(existing -> {
            existing.setNombre(u.getNombre());
            existing.setEmail(u.getEmail());
            existing.setPassword(u.getPassword());
            return usuarioRepository.save(existing);
        });
    }

    public boolean delete(Integer id) {
        return usuarioRepository.findById(Long.valueOf(id)).map(u -> {
            usuarioRepository.delete(u);
            return true;
        }).orElse(false);
    }
}
