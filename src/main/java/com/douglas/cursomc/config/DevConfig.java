package com.douglas.cursomc.config;

import com.douglas.cursomc.service.DBService;
import com.douglas.cursomc.service.EmailService;
import com.douglas.cursomc.service.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {
    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        if (!"create".equals(strategy)){
            return false;
        }
        dbService.instantiateTestDatabase();
        return true;
    }

    /**
     * Quando rodarmos nosso sistema no Perfil DEV.
     * A instancia de SmtpMailService será injetada pelo Framework.
     * Dessa forma a mudança do Mock para o Email de verdade será transparente.
     * @return SmtpEmailService.
     */
    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }
}
