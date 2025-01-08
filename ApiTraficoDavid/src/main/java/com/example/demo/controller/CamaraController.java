package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.Camara;
import com.example.demo.modelo.CamaraRepositorio;
import com.example.demo.modelo.Incidencia;
import com.example.demo.modelo.IncidenciaRepositorio;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CamaraController {

	private final CamaraRepositorio CamaraRepositorio;


	/**
	 * Obtenemos todos los productos
	 * 
	 * @return
	 */
	@GetMapping("/camaras")
    public List<Camara> obtenerTodos() {
        // Aqui tenemos que devolver todos los productos.
        return StreamSupport.stream(CamaraRepositorio.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
	/**
	 * Obtenemos un producto en base a su ID
	 * 
	 * @param id
	 * @return Null si no encuentra el producto
	 */
	@GetMapping("/camaras/{id}")
	public Camara obtenerUno(@PathVariable Long id) {
		// Vamos a modificar este código
		return CamaraRepositorio.findById(id).orElse(null); 
	}
	
	
	
	
	/**
	 * Insertamos un nuevo producto
	 * 
	 * @param nuevo
	 * @return producto insertado
	 */
	@PostMapping("/producto")
	public Camara nuevoProducto(@RequestBody Camara nuevo) {
		// Vamos a modificar este código
		return CamaraRepositorio.save(nuevo);
	}

	/**
	 * 
	 * @param editar
	 * @param id
	 * @return
	 */
	@PutMapping("/producto/{id}")
	public Camara editarProducto(@RequestBody Camara editar, @PathVariable Long id) {
		
		if(CamaraRepositorio.existsById(id)) {
		
			return CamaraRepositorio.save(editar);
		}else {
			return null; //Si no existe devolvemos null.
		}
		
	}

	/**
	 * Borra un producto del catálogo en base a su id
	 * @param id
	 * @return
	 */
	@DeleteMapping("/producto/{id}")
	public Camara borrarProducto(@PathVariable Long id) {
		if(CamaraRepositorio.existsById(id)) {
			Camara producto= CamaraRepositorio.findById(id).get();
			CamaraRepositorio.deleteById(id);
			return producto;
		}else {
			return null; 
		}
	}

}
