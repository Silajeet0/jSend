package com.jSend.emailclient;

import java.util.HashMap;
import java.util.Map;

public class MailConfigDetector {
    private static final Map<String, MailConfig> PRESET_CONFIGS = new HashMap<>();

    static {
        //Common email provider configurations
        PRESET_CONFIGS.put("gmail.com", new MailConfig("smtp.gmail.com", 587, "imap.gmail.com", 993));
        PRESET_CONFIGS.put("yahoo.com", new MailConfig("smtp.mail.yahoo.com", 587, "imap.mail.yahoo.com", 993));
        PRESET_CONFIGS.put("outlook.com", new MailConfig("smtp.office365.com", 587, "outlook.office365.com", 993));
        PRESET_CONFIGS.put("icloud.com", new MailConfig("smtp.mail.me.com", 587, "imap.mail.me.com", 993));
    }

    public static MailConfig detect(String email){

        if(email == null || !email.contains("@")){
            return null;
        }

        String domain = email.substring(email.indexOf('@') + 1);
        return PRESET_CONFIGS.getOrDefault(domain, null);
    }
}
