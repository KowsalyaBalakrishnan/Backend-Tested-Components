package com.async.practice.asyncimplservice.config;

import com.async.practice.asyncimplservice.service.NotificationService;
import com.async.practice.asyncimplservice.service.impl.EmailNotification;
import com.async.practice.asyncimplservice.service.impl.SmsNotification;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationServiceConfig {

    @Bean("SMS")
    @ConditionalOnExpression("${notification.email:false} && ${notification.sms:true}")
    public NotificationService getSms() {
        return new SmsNotification();
    }

    @Bean("Email")
    @ConditionalOnExpression("${notification.email:true} && ${notification.sms:false}")
    public NotificationService getEmail() {
        return new EmailNotification();
    }

}
