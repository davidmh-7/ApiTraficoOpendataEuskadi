package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.Camara;
import com.example.demo.modelo.Usuario;
import com.example.demo.modelo.UsuarioRepositorio;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/usuarios")
    public List<Usuario> obtenerTodos() {
        return StreamSupport.stream(usuarioRepositorio.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/usuarios/{id}")
	public Usuario obtenerUno(@PathVariable Long id) {
		// Vamos a modificar este código
		return usuarioRepositorio.findById(id).orElse(null); 
	}
}
