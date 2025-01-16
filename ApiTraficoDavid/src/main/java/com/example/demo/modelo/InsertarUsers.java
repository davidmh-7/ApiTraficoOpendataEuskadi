package com.example.demo.modelo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertarUsers {
	 @Bean
	    CommandLineRunner initDatabase(UsuarioRepositorio repository) {
	        return args -> {
	            Usuario usuario1 = new Usuario();
	            usuario1.setId(1L);
	            usuario1.setPassword("David");
	            usuario1.setCorreo("davidmangas@gmail.com");
	            usuario1.setUsuario("David");
	            repository.save(usuario1);

	            Usuario usuario2 = new Usuario();
	            usuario2.setId(2L);
	            usuario2.setPassword("1234");
	            usuario2.setCorreo("jorge@example.com");
	            usuario2.setUsuario("Jorge");
	            repository.save(usuario2);
	            
	            Usuario usuario3 = new Usuario();
	            usuario3.setId(3L);
	            usuario3.setPassword("1234");
	            usuario3.setCorreo("hugo@gamil.com");
	            usuario3.setUsuario("Hugo");
	            repository.save(usuario3);
	            
	            System.out.println("Usuarios insertados correctamente");
	        };
	      
	    }
}
