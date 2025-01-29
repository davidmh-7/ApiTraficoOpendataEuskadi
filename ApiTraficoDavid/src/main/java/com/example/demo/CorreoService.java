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
        final String contrasena = "hlgi iyii yboq jxox"; // ¡No olvides usar una contraseña segura!
        String asunto = "Cuenta creada";

        // Aquí está la plantilla HTML que me proporcionaste
        String cuerpoMensajeHTML = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "<script src='https://cdn.tailwindcss.com'></script>"
                + "</head>"
                + "<body>"
                + "<div class='max-w-2xl mx-auto p-6' style='max-width: 42rem; margin-left: auto; margin-right: auto; padding: 1.5rem;'>"
                + "<!-- Header -->"
                + "<div class='bg-gray-100 rounded-t-lg px-6 py-4' style='background-color: #f3f4f6; border-top-left-radius: 0.5rem; border-top-right-radius: 0.5rem; padding: 1rem 1.5rem;'>"
                + "<img src='https://via.placeholder.com/150x50' alt='Company Logo' class='h-8' style='height: 2rem;'>"
                + "</div>"
                + "<!-- Content -->"
                + "<div class='bg-white px-6 py-8' style='background-color: #ffffff; padding: 2rem 1.5rem;'>"
                + "<h1 class='text-2xl font-bold text-gray-900 mb-4' style='font-size: 1.5rem; font-weight: 700; color: #111827; margin-bottom: 1rem;'>"
                + "¡Bienvenido a Nuestra Plataforma VTFC!</h1>"
                + "<p class='text-gray-600 mb-6' style='color: #4b5563; margin-bottom: 1.5rem;'>"
                + "Nos complace darle la bienvenida a nuestra comunidad. Estamos emocionados de tenerle con nosotros y queremos asegurarnos de que tenga la mejor experiencia posible."
                + "</p>"
                + "<div class='bg-blue-50 border-l-4 border-blue-500 p-4 mb-6' style='background-color: #eff6ff; border-left: 4px solid #3b82f6; padding: 1rem; margin-bottom: 1.5rem;'>"
                + "<p class='text-blue-700' style='color: #1d4ed8;'>"
                + "Para comenzar, por favor verifique su cuenta haciendo clic en el botón de abajo."
                + "</p>"
                + "</div>"
                + "<!-- Button -->"
                + "<div class='text-center' style='text-align: center;'>"
                + "<a href='#' class='inline-block px-6 py-3 bg-blue-600 text-white font-semibold rounded-lg' style='display: inline-block; padding: 0.75rem 1.5rem; background-color: #2563eb; color: #ffffff; font-weight: 600; border-radius: 0.5rem; text-decoration: none;'>"
                + "Verificar mi cuenta"
                + "</a>"
                + "</div>"
                + "</div>"
                + "<!-- Footer -->"
                + "<div class='bg-gray-50 rounded-b-lg px-6 py-4 text-sm text-gray-500' style='background-color: #f9fafb; border-bottom-left-radius: 0.5rem; border-bottom-right-radius: 0.5rem; padding: 1rem 1.5rem; font-size: 0.875rem; color: #6b7280;'>"
                + "<p style='margin: 0;'>"
                + "Si no solicitó esta cuenta, puede ignorar este correo electrónico."
                + "</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

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

            // Establecer el contenido como HTML
            mensaje.setContent(cuerpoMensajeHTML, "text/html");

            // Enviar el mensaje
            Transport.send(mensaje);
            System.out.println("¡Correo enviado con éxito!");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}
