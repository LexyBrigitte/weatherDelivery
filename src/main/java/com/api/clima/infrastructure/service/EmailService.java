package com.api.clima.infrastructure.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    
    @Value("${weather.subject}")
    private String subject;
    
    private String textParam;
    
    
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String email, String text) {
    	textParam = "Hola! Tenemos programada la entrega de tu paquete para mañana. En la dirección de entrega esperamos un día con conditionText, por esta razón es posible que tengamos retrasos. Haremos todo a nuestro alcance para cumplir con tu entrega.";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(textParam.replace("conditionText", text));
        mailSender.send(message);
    }
}
