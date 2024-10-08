package com.application.uberApp.services;

public interface EmailSender {
    public void sendMail(String toEmail,String subject,String body);
    public void sendEmail(String[] toEmail,String subject,String body);
}
