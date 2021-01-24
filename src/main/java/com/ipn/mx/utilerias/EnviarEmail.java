/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.utilerias;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author werog
 */
public class EnviarEmail {

    public static void enviarCorreo(String destinatario, String asunto, String mensaje) {
        System.out.println("SE ENVIARA CORREO a " + destinatario);
        try {
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.host", "smtp.gmail.com");
            properties.setProperty("mail.smtp.starttls.enable", "true");
            properties.setProperty("mail.smtp.port", "587");
            properties.setProperty("mail.smtp.user", "cegswad@gmail.com");
            properties.setProperty("mail.smtp.auth", "true");
            String password = "Aquivalaclave1%";
            System.out.println("PASSWORD>>>>> " + password + " length " + password.length());
            //Session session = Session.getDefaultInstance(properties);
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("cegswad@gmail.com", "Aquivalaclave1%");
                }
            });
            MimeMessage elMensaje = new MimeMessage(session);
            
            elMensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            elMensaje.setSubject(asunto);
            elMensaje.setText(mensaje);
            
            Transport t = session.getTransport("smtp");
            t.connect(properties.getProperty("mail.smtp.user"), password);
            t.sendMessage(elMensaje, elMensaje.getAllRecipients());
            t.close();
        } catch (AddressException ex) {
            Logger.getLogger(EnviarEmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnviarEmail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static void main(String[] args) {
        String destinatario = "pequenobribon@gmail.com";
        String asunto = "prueba envio correo gmail 6";
        String message = "EStoy probando";
        System.out.println("Enviando correo");
        enviarCorreo(destinatario,asunto,message);
        System.out.println("Correo enviado");
    }
}
