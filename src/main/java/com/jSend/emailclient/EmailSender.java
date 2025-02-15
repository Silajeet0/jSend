package com.jSend.emailclient;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailSender {
   public static void sendEmail(MailConfig config, String userName, String passWord, String recipient, String subject, String messageText){
       Properties props = new Properties();
       props.put("mail.smtp.auth", true);
       props.put("mail.smtp.starttls.enable", "true");
       props.put("mail.smtp.host", config.smtpServer);
       props.put("mail.smtp.port", config.smtpPort);
       props.put("mail.smtp.ssl.trust", config.smtpServer);

       Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthenticator(){
                return new PasswordAuthentication(userName, passWord);
            }
       });

       try{
           Message message = new MimeMessage((session));
           message.setFrom(new InternetAddress(userName));
           message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
           message.setSubject(subject);
           message.setText((messageText));

           Transport.send(message);
           System.out.println("✅ Email sent successfully to: "+recipient);
       }catch (MessagingException e){
           System.err.println("❌ Failed to send the email: "+e.getMessage());
           e.printStackTrace();
       }
    }
}
