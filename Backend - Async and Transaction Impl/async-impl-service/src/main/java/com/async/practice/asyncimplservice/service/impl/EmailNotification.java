package com.async.practice.asyncimplservice.service.impl;

import com.async.practice.asyncimplservice.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class EmailNotification implements NotificationService {
    @Override
    public String sendNotification() {
        return "Email Notification triggered";
    }

    @Override
    public String notifyBy() {
        return "Email";
    }
}
