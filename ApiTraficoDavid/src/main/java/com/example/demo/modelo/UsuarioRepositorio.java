
package com.example.demo.modelo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Usuario findByCorreo(String correo);
}
