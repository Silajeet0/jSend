package com.jSend.emailclient;

import jakarta.mail.*;
import java.util.Properties;

public class EmailReceiver {
    public static void fetchEmails(MailConfig config, String username, String passWord){
        Properties props = new Properties();
        props.put("mail.imap.host", config.imapServer);
        props.put("mail.imap.port", config.imapPort);
        props.put("mail.imap.ssl.enable", "True");

        Session session = Session.getDefaultInstance(props);
        //throws checked exception hence explicitly handled
        try {
            Store store = session.getStore("imap");
            store.connect(username, passWord);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            System.out.println("📩 You have : "+messages.length+" emails");

            for(int i = 0; i < Math.min(5, messages.length); i++){
                System.out.println("From: "+messages[i].getFrom()[0]);
                System.out.println("Subject: "+messages[i].getSubject());
                System.out.println("-------------------------");
            }

            inbox.close();
            store.close();
        }catch (MessagingException e){
            System.err.println("❌Failed to fetch emails: "+e.getMessage());
            e.printStackTrace();
        }

    }
}
