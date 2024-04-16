package com.thebigfun.user_service.service.impl;import com.thebigfun.user_service.service.EmailService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.mail.SimpleMailMessage;import org.springframework.mail.javamail.JavaMailSender;import org.springframework.stereotype.Service;@Servicepublic class EmailServiceImpl implements EmailService {    private final JavaMailSender mailSender;    @Autowired    public EmailServiceImpl(JavaMailSender mailSender) {        this.mailSender = mailSender;    }    @Override    public void enviarCodigoRecuperacion(String destino, String codigo) {        SimpleMailMessage mensaje = new SimpleMailMessage();        mensaje.setTo(destino);        mensaje.setSubject("Código de Recuperación de Contraseña");        mensaje.setText("Su código de recuperación es: " + codigo);        mailSender.send(mensaje);    }}