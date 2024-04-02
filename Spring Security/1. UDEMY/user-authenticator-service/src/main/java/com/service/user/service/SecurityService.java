package com.service.user.service;

public interface SecurityService {

    boolean authenticateUserCredentials(String email, String password);
}
