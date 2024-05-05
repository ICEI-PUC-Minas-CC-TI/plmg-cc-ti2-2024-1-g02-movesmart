package model;

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

    // Change these variables to your email and password
    static final String SENDER_EMAIL = "your_email@example.com";
    static final String SENDER_PASSWORD = "your_password";

    public static void main(String[] args) {
        // Create a user object
        Usuario u = new Usuario();

        // Recipient's email
        String recipientEmail = u.getEmail();

        // Email properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.example.com"); // Change this to your SMTP server
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587"); // Change this if needed
        props.put("mail.smtp.starttls.enable", "true");

        // Authenticate the sender email and password
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        };

        // Create a session
        Session session = Session.getInstance(props, auth);

        try {
            // Create a MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(SENDER_EMAIL));

            // Set To: header field
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

            // Set Subject: header field
            message.setSubject("Seu onibus esta chegando!");

            // Set the email content
            String emailContent = "Seu onibus esta chegando!";
            message.setText(emailContent);

            // Send the email
            Transport.send(message);

            System.out.println("Aviso de chegada enviado com sucesso!");

        } catch (MessagingException e) {
            System.err.println("Error occurred while sending email: " + e.getMessage());
        }
    }
}