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
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author werog
 */
public class Utilerias {
    public static void enviarCorreo(String destinatario, String asunto, String mensaje) {
        try {
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.host", "smtp.gmail.com");
            properties.setProperty("mail.smtp.starttls.enable", "true");
            properties.setProperty("mail.smtp.port", "587");
            properties.setProperty("mail.smtp.user", "cegswad@gmail.com");
            properties.setProperty("mail.smtp.auth", "true");
            
            Session session = Session.getDefaultInstance(properties);
            MimeMessage elMensaje = new MimeMessage(session);
            
            elMensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            elMensaje.setSubject(asunto);
            elMensaje.setText(mensaje);
            
            Transport t = session.getTransport("smtp");
            t.connect(properties.getProperty("mail.smtp.user"), "Aquivalaclave%");
            t.sendMessage(elMensaje, elMensaje.getAllRecipients());
            t.close();
        } catch (AddressException ex) {
            Logger.getLogger(Utilerias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Utilerias.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
