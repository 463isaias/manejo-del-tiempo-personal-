package com.manejotiempo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.manejotiempo.model.Usuario;

@Service
public class UsuarioService {
    private final Map<Integer, Usuario> store = new ConcurrentHashMap<>();
    private final AtomicInteger idGen = new AtomicInteger(1);

    public List<Usuario> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Usuario> findById(Integer id) {
        return Optional.ofNullable(store.get(id));
    }

    public Usuario create(Usuario u) {
        int id = idGen.getAndIncrement();
        u.setUsuarioId(id);
        store.put(id, u);
        return u;
    }

    public Optional<Usuario> update(Integer id, Usuario u) {
        if (!store.containsKey(id)) return Optional.empty();
        u.setUsuarioId(id);
        store.put(id, u);
        return Optional.of(u);
    }

    public boolean delete(Integer id) {
        return store.remove(id) != null;
    }
}
