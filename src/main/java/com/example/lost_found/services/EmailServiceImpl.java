package com.example.lost_found.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl{
    @Autowired
    private JavaMailSender emailSender;

    public void sendEmailMessage(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("your-email");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
    }
}
