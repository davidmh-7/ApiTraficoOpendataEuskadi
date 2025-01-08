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

import com.example.demo.modelo.Incidencia;
import com.example.demo.modelo.IncidenciaRepositorio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Gestión de Incidencias", description = "Endpoints para administrar y gestionar incidencias sistema")
public class IncidenciaController {
	private final IncidenciaRepositorio incidenciaRepositorio;
	
	
	@Operation(
	        summary = "Muestra todas las inicidencias", 
	        description = "Obtiene todos las incidencias registradas hoy en el sistema",
	        tags = {"Gestión de Incidencias"}
	    )
	@GetMapping("/incidencia")
    public List<Incidencia> obtenerTodosInci() {
        // Aqui tenemos que devolver todos los productos.
        return StreamSupport.stream(incidenciaRepositorio.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
	
	  @Operation(
		        summary = "Muestra las inicidencias por ID", 
		        description = "Obtiene todos las incidencias registradas hoy en el sistema por ID",
		        tags = {"Gestión de Incidencias"}
		    )
	@GetMapping("/incidencia/{id}")
	public Incidencia obtenerUnoo(@PathVariable Long id) {
		// Vamos a modificar este código
		return incidenciaRepositorio.findById(id).orElse(null); 
	}
	
	  @Operation(
		        summary = "Elimina las incidencias", 
		        description = "Elimina las incidencias registradas hoy en el sistema por ID",
		        tags = {"Gestión de Incidencias"}
		    )
	 @DeleteMapping("/deleteIncidencia/{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void eliminarUsuario(@PathVariable Long id) {
		 incidenciaRepositorio.deleteById(id);
	    }
	 
	  @Operation(
		        summary = "Modifica las incidencias", 
		        description = "Modifica las incidencias por ID",
		        tags = {"Gestión de Incidencias"}
		    )
	  
	 @PutMapping("/modificarIncidencias/{id}")
	    public Incidencia modificarIncidencia(@PathVariable Long id, @RequestBody Incidencia incidencia) {
	        return incidenciaRepositorio.findById(id)
	                .map(i -> {
	                    i.setIncidenceId(incidencia.getIncidenceId());
	                    i.setSourceId(incidencia.getSourceId());
	                    i.setIncidenceType(incidencia.getIncidenceType());
	                    i.setAutonomousRegion(incidencia.getAutonomousRegion());
	                    i.setProvince(incidencia.getProvince());
	                    i.setCause(incidencia.getCause());
	                    i.setCityTown(incidencia.getCityTown());
	                    i.setStartDate(incidencia.getStartDate());
	                    i.setEndDate(incidencia.getEndDate());
	                    i.setPkStart(incidencia.getPkStart());
	                    i.setPkEnd(incidencia.getPkEnd());
	                    i.setDirection(incidencia.getDirection());
	                    i.setIncidenceName(incidencia.getIncidenceName());
	                    i.setLatitude(incidencia.getLatitude());
	                    i.setLongitude(incidencia.getLongitude());
	                    return incidenciaRepositorio.save(i);
	                })
	                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));
	    }

	  
	  	@Operation(
		        summary = "Inserta las incidencias", 
		        description = "Inserta incidecias nuevas",
		        tags = {"Gestión de Incidencias"}
		    )
	    @PostMapping("/insertarIncidencia")
	    @ResponseStatus(HttpStatus.CREATED)
	    public Incidencia insertarIncidencia(@RequestBody Incidencia incidencia) {
	        return incidenciaRepositorio.save(incidencia);
	    }
	 
	
	
	
}
