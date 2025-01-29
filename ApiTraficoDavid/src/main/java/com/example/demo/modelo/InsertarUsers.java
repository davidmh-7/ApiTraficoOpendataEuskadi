package com.example.demo.modelo;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertarUsers {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepositorio repository) {
        return args -> {

           
            if (repository.count() == 0) { 
                Usuario usuario1 = new Usuario();
                usuario1.setId(1L);
                usuario1.setPassword(DigestUtils.sha1Hex("Eltermometros1."));
                usuario1.setCorreo("davidmangashernandez193@gmail.com");
                usuario1.setUsuario("David");
                repository.save(usuario1);

                Usuario usuario2 = new Usuario();
                usuario2.setId(2L);
                usuario2.setPassword(DigestUtils.sha1Hex("leunam"));
                usuario2.setCorreo("ikcte@plaiaundi.net");
                usuario2.setUsuario("Jorge");
                repository.save(usuario2);

                Usuario usuario3 = new Usuario();
                usuario3.setId(3L);
                usuario3.setPassword(DigestUtils.sha1Hex("Patata"));
                usuario3.setCorreo("ikcsx@plaiaundi.net");
                usuario3.setUsuario("Hugo");
                repository.save(usuario3);

                System.out.println("Usuarios insertados correctamente");
            } else {
                System.out.println("Los usuarios ya están insertados, no se añaden de nuevo.");
            }
        };
    }
}
