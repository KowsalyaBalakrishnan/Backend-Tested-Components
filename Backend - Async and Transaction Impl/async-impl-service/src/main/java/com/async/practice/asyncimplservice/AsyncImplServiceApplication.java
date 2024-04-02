package com.async.practice.asyncimplservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@PropertySource("file:${app.config.dir}/eLabel/application.properties")
public class AsyncImplServiceApplication implements CommandLineRunner {

    @Value("${server.port}")
    private String serverPort;

    @Value("${jasypt.encryptor.algorithm}")
    private String algorithm;

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(AsyncImplServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("serverPort:=======> " + serverPort);
        System.out.println("Algorithm: " + algorithm);
        System.out.println(environment.getProperty("constraint.exce.ail"));
    }
}
