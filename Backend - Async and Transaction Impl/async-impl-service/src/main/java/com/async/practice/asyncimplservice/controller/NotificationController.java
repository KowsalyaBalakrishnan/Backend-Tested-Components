package com.async.practice.asyncimplservice.controller;

import com.async.practice.asyncimplservice.service.ProcessNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationController {

    @Autowired
    private ProcessNotification processNotification;

    @GetMapping
    public ResponseEntity<String> sendNotification() {
        String notificationMessage = processNotification.proceed();
        return new ResponseEntity<>(notificationMessage, HttpStatus.OK);
    }


}
