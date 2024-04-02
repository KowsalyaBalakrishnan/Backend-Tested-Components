package com.service.coupon.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder getEncodedPassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin();

        httpSecurity.authorizeHttpRequests()
                .mvcMatchers(HttpMethod.GET, "/coupon/retrieve/{code:^[A-Z]*$}", "/")
                .hasAnyRole("USER", "ADMIN");

        httpSecurity.authorizeHttpRequests()
                .mvcMatchers(HttpMethod.POST, "/coupon/add")
                .hasRole("ADMIN");

        httpSecurity.csrf().disable();

        return httpSecurity.build();
    }
}
