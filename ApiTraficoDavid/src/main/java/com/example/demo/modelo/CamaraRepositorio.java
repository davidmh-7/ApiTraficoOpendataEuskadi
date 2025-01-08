package com.example.demo.modelo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CamaraRepositorio extends JpaRepository<Camara, Long> {

	void deleteAll();
}
