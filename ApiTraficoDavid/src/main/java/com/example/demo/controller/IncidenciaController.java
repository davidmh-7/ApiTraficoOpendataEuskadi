package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.Incidencia;
import com.example.demo.modelo.IncidenciaRepositorio;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class IncidenciaController {
	private final IncidenciaRepositorio incidenciaRepositorio;
	

	/**
	 * Obtenemos todos los productos
	 * 
	 * @return
	 */
	@GetMapping("/incidencia")
    public List<Incidencia> obtenerTodosInci() {
        // Aqui tenemos que devolver todos los productos.
        return StreamSupport.stream(incidenciaRepositorio.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
	
	/**
	 * Obtenemos un producto en base a su ID
	 * 
	 * @param id
	 * @return Null si no encuentra el producto
	 */
	@GetMapping("/incidencia/{id}")
	public Incidencia obtenerUnoo(@PathVariable Long id) {
		// Vamos a modificar este código
		return incidenciaRepositorio.findById(id).orElse(null); 
	}
	
	
	
}
