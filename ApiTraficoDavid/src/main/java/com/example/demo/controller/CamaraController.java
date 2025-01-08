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

import com.example.demo.modelo.Camara;
import com.example.demo.modelo.CamaraRepositorio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Gestión de Cámaras", description = "Endpoints para administrar y controlar las cámaras de trafico")
public class CamaraController {

	private final CamaraRepositorio camaraRepositorio;

	@Operation(
	        summary = "Muestra todas las camaras", 
	        description = "Obtiene todos las camaras registradas en el sistema",
	        tags = {"Gestión de Cámaras"}
	    )

	@GetMapping("/camaras")
    public List<Camara> obtenerTodos() {
        // Aqui tenemos que devolver todos los productos.
        return StreamSupport.stream(camaraRepositorio.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
	
	
	@Operation(summary = "Muestra las camaras por ID", description = "Obtiene todos las camaras registradas en el sistema por ID", tags = {
			"Gestión de Cámaras" })
	@GetMapping("/camaras/{id}")
	public Camara obtenerUno(@PathVariable Long id) {
		// Vamos a modificar este código
		return camaraRepositorio.findById(id).orElse(null); 
	}
	
	
	
	@Operation(summary = "Elimina las camaras", description = "Elimina las camaras registradas en el sistema por ID", tags = {
			"Gestión de Cámaras" })
	 @DeleteMapping("/delateCamara/{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void eliminarCamara(@PathVariable Long id) {
	        camaraRepositorio.deleteById(id);
	    }

	@Operation(summary = "Modifica las camaras", description = "Modifica las camaras registradas en el sistema por ID", tags = {
	"Gestión de Cámaras" })
	
	    @PutMapping("/modificarCamara/{id}")
	    public Camara modificarCamara(@PathVariable Long id, @RequestBody Camara camara) {
	        return camaraRepositorio.findById(id)
	                .map(c -> {
	                    c.setCameraId(camara.getCameraId());
	                    c.setSourceId(camara.getSourceId());
	                    c.setCameraName(camara.getCameraName());
	                    c.setUrlImage(camara.getUrlImage());
	                    c.setLatitude(camara.getLatitude());
	                    c.setLongitude(camara.getLongitude());
	                    c.setKilometer(camara.getKilometer());
	                    c.setAddress(camara.getAddress());
	                    c.setRoad(camara.getRoad());
	                    return camaraRepositorio.save(c);
	                })
	                .orElseThrow(() -> new RuntimeException("Camara no encontrada"));
	    }
	
	

	@Operation(summary = "Inserta las camaras", description = "Inserta las camaras en el sistema", tags = {
	"Gestión de Cámaras" })
	    @PostMapping("/insertarCamara")
	    @ResponseStatus(HttpStatus.CREATED)
	    public Camara insertarCamara(@RequestBody Camara camara) {
	        return camaraRepositorio.save(camara);
	    }
    
	

}
