
package com.example.demo.modelo;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepositorio userRepository;

    public boolean login(String correo, String password) {
        String hashedPassword = DigestUtils.sha1Hex(password);
        Usuario user = userRepository.findByCorreo(correo);
        if (user != null) {
            return hashedPassword.equals(user.getPassword());
        }
        return false;
    }
}
