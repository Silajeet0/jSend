package com.jSend.emailclient;

//configuration class to store imap and stmp server details
public class MailConfig
{
    //smtp server address
    public String smtpServer;
    //smtp server port
    public int smtpPort;
    //imap server address
    public String imapServer;
    //imap server port
    public int imapPort;

    //Constructor to initialize the MailConfig object with SMTP and IMAP server details
    public MailConfig(String smtpServer, int smtpPort, String imapServer, int imapPort)
    {
        this.smtpServer = smtpServer;
        this.smtpPort = smtpPort;
        this.imapServer = imapServer;
        this.imapPort = imapPort;
    }

    //Returns a string representation of the MailConfig object
    @Override
    public String toString(){
        return "SMTP: "+smtpServer+" : "+smtpPort+", IMAP: "+imapServer+" : "+imapPort;
    }

}
