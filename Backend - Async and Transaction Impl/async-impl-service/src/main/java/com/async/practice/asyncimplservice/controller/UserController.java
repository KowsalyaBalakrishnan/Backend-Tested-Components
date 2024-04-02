package com.async.practice.asyncimplservice.controller;

import com.async.practice.asyncimplservice.dto.UserDto;
import com.async.practice.asyncimplservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody List<UserDto> userRequest) {
        userService.createUsers(userRequest);
        return new ResponseEntity<>("Users are created", HttpStatus.CREATED);
    }

    @GetMapping("/hello")
    public String greet() {
        return "Good Eve!";
    }
}
