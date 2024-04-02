package com.async.practice.asyncimplservice.service;

import com.async.practice.asyncimplservice.dto.UserDto;
import com.async.practice.asyncimplservice.entity.User;
import com.async.practice.asyncimplservice.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Async
    public void createUsers(List<UserDto> userRequest) {

        log.info(Thread.currentThread().getName() + " Execution is Started...");

        // Injecting a delay manually to mimic some background processing
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<User> userList = userRequest.stream()
                .map(data -> {
                    User user = new User();
                    user.setName(data.getName());
                    user.setEmailId(data.getEmailId());
                    user.setCity(data.getCity());
                    return user;
                }).collect(Collectors.toList());

        userRepository.saveAll(userList);

        log.info(Thread.currentThread().getName() + " Execution is Completed...");
    }
}
