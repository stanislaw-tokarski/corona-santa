package com.github.stanislawtokarski.corona.santa.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Base64;
import java.util.Properties;

@Component
public class SantaMailSenderImpl implements MailSender {

    private static final Base64.Decoder DECODER = Base64.getDecoder();
    private static final String COLON = ":";

    private final JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
    private final Properties properties = new Properties();
    private final String username;
    private final char[] password;

    @Autowired
    public SantaMailSenderImpl(@Value("${mail.credentials}") String credentials) {
        var credentialsSplitted = new String(DECODER.decode(credentials)).split(COLON);
        this.username = credentialsSplitted[0];
        this.password = new String(DECODER.decode(credentialsSplitted[1])).toCharArray();
    }

    @PostConstruct
    private void configureSender() {
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        javaMailSender.setJavaMailProperties(properties);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(new String(password));
    }

    @Override
    public void push(String recipient, String subject, String body) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(username);
        helper.setSubject(subject);
        helper.setText(body, true);
        helper.addTo(recipient);
        javaMailSender.send(message);
    }
}
