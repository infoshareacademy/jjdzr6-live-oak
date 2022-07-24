package com.infoshareacademy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Mailer implements CommandLineRunner {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(
            String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        System.out.println("Wysylamy");
        message.setFrom("servicebox-liveoak@wp.pl");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        try {
            emailSender.send(message);
        } catch (MailException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
                    }
        System.out.println("OK");
    }

    @Override
    public void run(String... args) throws Exception {

        sendSimpleMessage("analysis-jk@gmail.com", "Testowy mail", "Witamy");


    }
}


