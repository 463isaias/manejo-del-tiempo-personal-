package com.manejotiempo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.manejotiempo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Aquí puedes agregar métodos personalizados si los necesitas
}
