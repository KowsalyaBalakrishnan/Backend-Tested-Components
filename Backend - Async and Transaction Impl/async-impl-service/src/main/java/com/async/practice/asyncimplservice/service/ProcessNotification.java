package com.async.practice.asyncimplservice.service;

import com.async.practice.asyncimplservice.config.NotificationServiceConfig;
import com.async.practice.asyncimplservice.service.factory.NotificationServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessNotification {

    @Autowired
    private NotificationServiceFactory notificationServiceFactory;

    @Autowired
    private NotificationServiceConfig notificationServiceConfig;

    public String proceed() {

        NotificationService notificationService = notificationServiceFactory.getNotificationServiceObject("SMS");

        return notificationService.sendNotification();
    }
}
