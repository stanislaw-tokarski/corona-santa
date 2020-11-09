package com.github.stanislawtokarski.corona.santa.mail;

import javax.mail.MessagingException;

public interface MailSender {

    void push(String recipient, String subject, String body) throws MessagingException;
}
