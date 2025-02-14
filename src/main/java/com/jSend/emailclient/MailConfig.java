package com.jSend.emailclient;

public class MailConfig {
    public String smtpServer;
    public int smtpPort;
    public String imapServer;
    public int imapPort;

    public MailConfig(String smtpServer, int smtpPort, String imapServer, int imapPort){
        this.smtpServer = smtpServer;
        this.smtpPort = smtpPort;
        this.imapServer = imapServer;
        this.imapPort = imapPort;
    }

    @Override
    public String toString(){
        return "SMTP: "+smtpServer+" : "+smtpPort+", IMAP: "+imapServer+" : "+imapPort;
    }

}
