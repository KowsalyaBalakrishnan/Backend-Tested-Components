package com.async.practice.asyncimplservice.service.impl;

import com.async.practice.asyncimplservice.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class SmsNotification implements NotificationService {
    @Override
    public String sendNotification() {
        return "SMS Notification triggered";
    }

    @Override
    public String notifyBy() {
        return "SMS";
    }
}
