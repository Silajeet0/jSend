package com.jSend.emailclient;
import java.util.Scanner;
import jakarta.mail.*;


public class EmailClient {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your e-mail id : ");
        String email = sc.nextLine();

        System.out.println("Enter your password: ");
        String password = sc.nextLine();

        MailConfig config = MailConfigDetector.detect(email);

        if(config == null){
            System.out.println("Auto Detection failed !! Please enter your mail server details manually: ");
            String smtpServer = sc.nextLine();

            System.out.println("IMAP server: ");
            String imapServer = sc.nextLine();

            config = new MailConfig(smtpServer, 587, imapServer, 993);
        }

        System.out.println("Configuration detected: "+config);

        EmailSender.sendEmail(config, email, password, "RECIPIENT_MAIL_ID", "Test Email", "Hello from Java");

        EmailReceiver.fetchEmails(config, email, password);
    }
}
