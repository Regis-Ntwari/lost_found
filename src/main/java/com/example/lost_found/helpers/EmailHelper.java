package com.example.lost_found.helpers;
import java.util.Properties;

// import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class EmailHelper {
    
    private String host = "smtp.gmail.com";
    private int port = 587;
    private String username = "your-email";
    private String password = "your-password";

    @Bean
    public JavaMailSender sendEmail(){
        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setHost(host);
        mailSenderImpl.setPort(port);
        mailSenderImpl.setUsername(username);
        mailSenderImpl.setPassword(password);

        Properties properties = mailSenderImpl.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");

        return mailSenderImpl;


    }
}
