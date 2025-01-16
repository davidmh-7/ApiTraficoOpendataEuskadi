package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.Usuario;
import com.example.demo.modelo.UsuarioRepositorio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Gestión de Usuarios", description = "Endpoints para administrar usuarios del sistema")
public class UsuarioController {

    private final UsuarioRepositorio usuarioRepositorio;

    
    
	@Operation(summary = "Muestra todos los usuarios", description = "Obtiene todos los usuarios registrados en el sistema", tags = {
			"Gestión de Usuarios" })
    @GetMapping("/usuarios")
    public List<Usuario> obtenerTodos() {
        return StreamSupport.stream(usuarioRepositorio.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
    
    
    @Operation(
	        summary = "Muestra los usuarios por ID", 
	        description = "Obtiene todos los usuarios registrados en el sistema por ID",
	        tags = {"Gestión de Usuarios"}
	    )
    @GetMapping("/usuarios/{id}")
	public Usuario obtenerUno(@PathVariable Long id) {
		// Vamos a modificar este código
		return usuarioRepositorio.findById(id).orElse(null); 
	}
    
    
    
    @Operation(
	        summary = "Elimina los usuarios", 
	        description = "Elimina los usuarios del sistema por ID",
	        tags = {"Gestión de Usuarios"}
	    )
    
    @DeleteMapping("/deleteUsuarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioRepositorio.deleteById(id);
    }
    
    
    @Operation(
	        summary = "Modifica los usuarios", 
	        description = "Modifica los usuarios por ID",
	        tags = {"Gestión de Usuarios"}
	    )
    @PutMapping("/ModificarUsuarios/{id}")
    public Usuario modificarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioRepositorio.findById(id)
                .map(u -> {
                    // Solo modificamos los campos que realmente nos pasan
                    if (usuario.getUsuario() != null) {
                        u.setUsuario(usuario.getUsuario());
                    }
                    if (usuario.getCorreo() != null) {
                        u.setCorreo(usuario.getCorreo());
                    }
                    if (usuario.getPassword() != null) {
                        u.setPassword(usuario.getPassword());
                    }
                    return usuarioRepositorio.save(u);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    
	@Operation(
	        summary = "Insertar usuarios", 
	        description = "Inserta usuarios nuevos en el sistema",
	        tags = {"Gestión de Usuarios"}
	    )
    
    @PostMapping("/insertarUsuario")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario insertarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

   
    
}
