package com.preet.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("Email")
@Primary
public class EmailNotificationService implements  NotificationService{

    @Value("${email.host}")
    private String host;

    @Value("${email.port}")
    private int port;

    public void send(String msg,String recipientEmail)
    {
        System.out.println("Email sent on : "+host+":"+port);
        System.out.println("  Message: "+ msg);
        System.out.println(" Sent to "+recipientEmail);
    }
}
