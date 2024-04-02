package com.async.practice.asyncimplservice.service.factory;

import com.async.practice.asyncimplservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationServiceFactory {

    @Autowired
    List<NotificationService> notificationServices;

    private Map<String, NotificationService> objects;

    @PostConstruct
    public void initialize() {
        objects = new HashMap<>();
        notificationServices.forEach(obj -> objects.put(obj.notifyBy(), obj));
    }

    public NotificationService getNotificationServiceObject(String option) {
        return objects.get(option);
    }
}
