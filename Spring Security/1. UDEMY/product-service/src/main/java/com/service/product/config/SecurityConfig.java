package com.service.product.config;

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
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();
        httpSecurity.authorizeHttpRequests()
                .mvcMatchers(HttpMethod.POST, "/product/add")
                .hasRole("ADMIN");

        httpSecurity.authorizeHttpRequests()
                .mvcMatchers(HttpMethod.GET, "/product/retrieve/{id:^[0-9]*$}")
                .hasAnyRole("USER", "ADMIN");

        httpSecurity.csrf().disable();

        return httpSecurity.build();
    }
}
