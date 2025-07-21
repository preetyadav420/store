package com.preet.store;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationManager {

    NotificationService notificationService;

    public NotificationManager(@Qualifier("SMS") NotificationService notificationService)
    {
        this.notificationService = notificationService;
    }

    public void sendNotification(String msg){
        notificationService.send(msg, "Test");
    }
}
