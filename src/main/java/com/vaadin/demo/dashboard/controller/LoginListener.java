/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.demo.dashboard.controller;

import com.vaadin.demo.dashboard.DashboardUI;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 *
 * @author muaz.cisse
 */
@Component
public class LoginListener implements Button.ClickListener {

    @Autowired
    private final AuthManager authManager = new AuthManager();

    @Override
    public void buttonClick(Button.ClickEvent event) {
        try {

            Button source = event.getButton();

            String username = (String) ((DashboardUI) UI.getCurrent()).getSession().getAttribute("username");
            String password = (String) ((DashboardUI) UI.getCurrent()).getSession().getAttribute("password");
            authManager.handleAuthentication(username, password, RequestHolder.getRequest());

            DashboardUI current = (DashboardUI) UI.getCurrent();
            Navigator navigator = current.getNavigator();
            navigator.navigateTo("dashboard");
        }
        catch (AuthenticationException e) {
            Notification.show("Authentication failed: "
                    + e.getMessage());
        }
    }
}
