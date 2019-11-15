package com.douglas.cursomc.config;

import com.douglas.cursomc.service.DBService;
import com.douglas.cursomc.service.EmailService;
import com.douglas.cursomc.service.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TesteConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateTestDatabase();
        return true;
    }
    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }
}
