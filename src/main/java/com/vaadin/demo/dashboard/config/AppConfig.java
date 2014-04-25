/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.demo.dashboard.config;

import com.vaadin.demo.dashboard.controller.AuthManager;
import com.vaadin.demo.dashboard.controller.UserService;
import com.vaadin.demo.dashboard.listener.LoginListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author muaz.cisse
 */
@Configuration
@ComponentScan(basePackages = {"com.app.ui", "com.app.auth", "com.app.service"})
public class AppConfig {

    @Bean
    public AuthManager authManager() {
        return new AuthManager();
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public LoginListener loginListener() {
        return new LoginListener();
    }

}
