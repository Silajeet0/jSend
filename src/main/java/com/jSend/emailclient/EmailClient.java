package com.jSend.emailclient;
import java.util.Scanner;
import jakarta.mail.*;

// main class for email-client
public class EmailClient {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        // prompt user to enter their mail id
        System.out.println("Enter your e-mail id : ");
        String email = sc.nextLine();

        // Prompt the user to enter their email password
        System.out.println("Enter your password: ");
        String password = sc.nextLine();

        // Attempt to auto-detect mail server configuration based on email ID
        MailConfig config = MailConfigDetector.detect(email);

        // If auto-detection fails, prompt the user to manually enter server details
        if(config == null){
            System.out.println("Auto Detection failed !! Please enter your mail server details manually: ");

            // Prompt for SMTP server details
            System.out.println("SMTP server: ");
            String smtpServer = sc.nextLine();

            // Prompt for IMAP server details
            System.out.println("IMAP server: ");
            String imapServer = sc.nextLine();

            // Create a mail configuration object with user-provided details
            config = new MailConfig(smtpServer, 587, imapServer, 993);
        }

        // Display the detected or entered mail server configuration
        System.out.println("Configuration detected: "+config);

        // Send a test email using the provided email credentials and configuration
        EmailSender.sendEmail(config, email, password, "RECIPIENT_MAIL_ID", "Test Email", "Hello from Java");

        // Fetch emails from the inbox using the provided email credentials and configuration
        EmailReceiver.fetchEmails(config, email, password);
    }
}
