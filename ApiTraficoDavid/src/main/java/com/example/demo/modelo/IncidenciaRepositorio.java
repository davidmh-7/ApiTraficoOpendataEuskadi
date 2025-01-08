package com.example.demo.modelo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidenciaRepositorio extends JpaRepository<Incidencia, Long> {
	void deleteAll();
}
