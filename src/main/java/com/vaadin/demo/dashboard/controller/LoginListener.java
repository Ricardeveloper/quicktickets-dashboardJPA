/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.demo.dashboard.controller;

import com.vaadin.demo.dashboard.view.form.LoginForm;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author muaz.cisse
 */
@Component
public class LoginListener implements Button.ClickListener {

    @Autowired
    private AuthManager authManager;

    @Override
    public void buttonClick(Button.ClickEvent event) {
        try {
            Button source = event.getButton();
            //LoginForm parent = (LoginForm) source.getParent();
            LoginForm parent = (LoginForm) source.getParent().getParent();

            String username = parent.getUsername().getValue();

            System.out.println(username);
            //String username = parent.getTxtLogin().getValue();
            //String password = parent.getTxtPassword().getValue();
            //UsernamePasswordAuthenticationToken request = new UsernamePasswordAuthenticationToken(username, password);
            System.out.println("MUZZZ");
            UsernamePasswordAuthenticationToken request = new UsernamePasswordAuthenticationToken("Muaz", "1234");
            Authentication result = authManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            //AppUI current = (AppUI) UI.getCurrent();
            //Navigator navigator = current.getNavigator();
            //navigator.navigateTo("user");
        }
        catch (AuthenticationException e) {
            Notification.show("Authentication failed: "
                    + e.getMessage());
        }
    }
}
