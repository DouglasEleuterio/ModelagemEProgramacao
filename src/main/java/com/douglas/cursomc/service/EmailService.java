package com.douglas.cursomc.service;

import com.douglas.cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {
    void sendOrderConfirmationEmail (Pedido pedido);

    void sendEmail(SimpleMailMessage msg);

    void sendOrderConfirmationHtmlEmail(Pedido obj);

    void sendHtmlEmail (MimeMessage msg);
}
