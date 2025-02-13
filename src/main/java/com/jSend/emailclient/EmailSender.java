package com.jSend.emailclient;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailSender {
    public static void main(String[] args) {
        // SMTP server settings
        String host = "smtp.gmail.com";  // Change if using another provider
        String username = "silajeetbanerjee100@gmail.com";
        String password = "jktb kyhl bcbi xqbp"; // Use App Password instead of real password
        String recipient = "ignite6289@gmail.com";

        // Set mail properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", host);

        // Create a session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Enable debug mode for detailed output
            session.setDebug(true);

            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));  // Set sender email
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Test Email from Java");
            message.setText("Hello, this is a test email sent from Java!");

            // Send the email
            Transport.send(message);
            System.out.println("✅ Email sent successfully!");
        } catch (MessagingException e) {
            System.err.println("❌ Failed to send email. Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
