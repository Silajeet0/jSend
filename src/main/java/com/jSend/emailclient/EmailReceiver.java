package com.jSend.emailclient;

import jakarta.mail.*;
import java.util.Properties;

public class EmailReceiver {
    /**
     * Fetches emails from an IMAP server and prints basic details of up to 5 recent messages.
     *
     * @param config   Configuration for the IMAP server (host, port, etc.)
     * @param username The username for authentication
     * @param passWord The password for authentication
     */

    public static void fetchEmails(MailConfig config, String username, String passWord){

        //Setting properties for the imap server
        Properties props = new Properties();
        props.put("mail.imap.host", config.imapServer);
        props.put("mail.imap.port", config.imapPort);
        props.put("mail.imap.ssl.enable", "True");

        //create a new session with the specified properties mentioned above
        Session session = Session.getDefaultInstance(props);

        //throws checked exception hence explicitly handled
        //Connect to imap server and fetch the emails from the mail server
        try {

            //store object for storing the session if connection is successful
            Store store = session.getStore("imap");
            store.connect(username, passWord); //connect using the provided details

            //Access the inbox of the recipient
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY); //read-only mode accessed

            //fetch all messages from the inbox
            Message[] messages = inbox.getMessages();
            System.out.println("📩 You have : "+messages.length+" emails");

            //printing the details of first 5 messages from the inbox
            for(int i = 0; i < Math.min(5, messages.length); i++){
                System.out.println("From: "+messages[i].getFrom()[0]);
                System.out.println("Subject: "+messages[i].getSubject());
                System.out.println("-------------------------");
            }

            //close the inbox and session respectively
            inbox.close();
            store.close();
        }catch (MessagingException e){
            System.err.println("❌Failed to fetch emails: "+e.getMessage());
            e.printStackTrace();
        }

    }
}
