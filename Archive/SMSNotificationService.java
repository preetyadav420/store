package com.preet.store;

import org.springframework.stereotype.Service;

@Service("SMS")
public class SMSNotificationService implements NotificationService{
    public void send(String msg, String recipientEmail)
    {
        System.out.println("SMS: Message: "+msg);
    }
}
