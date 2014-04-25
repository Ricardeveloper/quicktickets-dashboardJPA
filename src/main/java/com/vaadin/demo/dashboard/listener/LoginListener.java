/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.demo.dashboard.listener;

import com.google.common.eventbus.Subscribe;
import com.vaadin.demo.dashboard.DashboardUI;
import com.vaadin.demo.dashboard.controller.AuthManager;
import com.vaadin.demo.dashboard.controller.RequestHolder;
import com.vaadin.demo.dashboard.event.LoginEvent;
import com.vaadin.demo.dashboard.view.DashboardView;
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
public class LoginListener extends GandallListener {

    @Autowired
    private final AuthManager authManager = new AuthManager();

    @Subscribe
    @Override
    public void buttonClick(Button.ClickEvent event) {
        try {

            String username = (String) ((DashboardUI) UI.getCurrent()).getSession().getAttribute("username");
            String password = (String) ((DashboardUI) UI.getCurrent()).getSession().getAttribute("password");

            LoginEvent loginEvent = new LoginEvent(username, password);

            getEventBus().post(loginEvent);

            authManager.handleAuthentication(username, password, RequestHolder.getRequest());

            DashboardUI current = (DashboardUI) UI.getCurrent();
            Navigator navigator = current.getNavigator();
            navigator.navigateTo(DashboardView.getViewName());
        }
        catch (AuthenticationException e) {
            Notification.show("Authentication failed: "
                    + e.getMessage());
        }
    }
}
