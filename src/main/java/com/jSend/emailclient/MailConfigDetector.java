package com.jSend.emailclient;

import java.util.HashMap;
import java.util.Map;

//class to detect email server configurations based on the email domain
public class MailConfigDetector {
    private static final Map<String, MailConfig> PRESET_CONFIGS = new HashMap<>();

    //initializing predefined configurations
    static {
        //Common email provider configurations
        PRESET_CONFIGS.put("gmail.com", new MailConfig("smtp.gmail.com", 587, "imap.gmail.com", 993));
        PRESET_CONFIGS.put("yahoo.com", new MailConfig("smtp.mail.yahoo.com", 587, "imap.mail.yahoo.com", 993));
        PRESET_CONFIGS.put("outlook.com", new MailConfig("smtp.office365.com", 587, "outlook.office365.com", 993));
        PRESET_CONFIGS.put("icloud.com", new MailConfig("smtp.mail.me.com", 587, "imap.mail.me.com", 993));
    }

    //Detects the mail server configuration based on the email domain
    public static MailConfig detect(String email){

        //check for invalid email missing '@'
        if(email == null || !email.contains("@")){
            return null;
        }

        //Extract the domain from the email address (part after '@')
        String domain = email.substring(email.indexOf('@') + 1);
        return PRESET_CONFIGS.getOrDefault(domain, null); //return the corresponding mailconfig if found else null
    }
}
