
package com.example.demo;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

@Service
public class CorreoService {

    public void enviarCorreoCreacionCuenta(String correoDestino) {
        String hostSMTP = "smtp.gmail.com";
        final String correoOrigen = "hugoalejos10@gmail.com";
        final String contrasena = "hlgi iyii yboq jxox";
        String asunto = "Cuenta creada";
        String cuerpoMensaje = "Su cuenta ha sido registrada exitosamente.";

        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.host", hostSMTP);
        propiedades.put("mail.smtp.port", "587");
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session sesion = Session.getInstance(propiedades, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correoOrigen, contrasena);
            }
        });

        try {
            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(correoOrigen));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestino));
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpoMensaje);

            Transport.send(mensaje);
            System.out.println("¡Correo enviado con éxito!");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}
