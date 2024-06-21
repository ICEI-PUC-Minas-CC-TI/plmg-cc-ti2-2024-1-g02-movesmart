package model;

// https://mailtrap.io/blog/sending-email-using-java/?utm_source=youtube&utm_medium=videos&utm_campaign=java_send_email_api

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    public static void sendEmail(String to, String subject, String text) {

        // provide sender email ID
        String user_from = "movesmartti2@gmail.com";

        // provide mailtrap's username
        final String username = "Vinishow";
        final String password = "TI2CC@2024";

        // provide mailtraps host address
        String host = "smtp.mailtrap.io";

        // configure mailtrap's SMTP server details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "6796");

        // create the session object
        Session session = Session.getInstance(props, 
            new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
        });
        try {
            // create a MimeMessage object
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user_from));
            message.setRecipients(( Message.RecipientType.TO), InternetAddress.parse(to));

            // The message and the subject is in the signature of the method
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            System.out.println("Email sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String subject = "Test Email"; // The subject
        String text = "Hello, this is a test email"; // The message
        String to = "giuseppe.cordeiro@gmail.com"; // The recipient change after to usuario.getemail()
        sendEmail(to, subject, text);
    }
}