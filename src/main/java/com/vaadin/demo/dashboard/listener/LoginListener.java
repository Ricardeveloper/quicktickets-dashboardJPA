package com.vaadin.demo.dashboard.listener;

import com.google.common.eventbus.Subscribe;
import com.vaadin.demo.dashboard.DashboardUI;
import com.vaadin.demo.dashboard.controller.RequestHolder;
import com.vaadin.demo.dashboard.event.LoginEvent;
import com.vaadin.demo.dashboard.service.AuthenticationService;
import com.vaadin.demo.dashboard.service.impl.AuthenticationServiceImpl;
import com.vaadin.demo.dashboard.util.ControlHelper;
import com.vaadin.demo.dashboard.view.DashboardView;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;

/**
 * @author Muaz Cisse
 */
@Controller
public class LoginListener extends GandallListener implements Serializable {

    @Autowired
    private final AuthenticationService authManager = new AuthenticationServiceImpl();

    @Subscribe
    @Override
    public void buttonClick(Button.ClickEvent event) {
        try {

            Button source = event.getButton();

            HorizontalLayout loginForm = (HorizontalLayout) source.getParent();

            String username = ((TextField) loginForm.getComponent(0)).getValue();
            String password = ((PasswordField) loginForm.getComponent(1)).getValue();

            LoginEvent loginEvent = (LoginEvent) getApplicationContext().getBean(ControlHelper.getClassLowerCamelName(LoginEvent.class), new Object[]{username, password});

            getEventBus().post(loginEvent);

            authManager.handleAuthentication(loginEvent, RequestHolder.getRequest());

            DashboardUI current = (DashboardUI) UI.getCurrent();
            Navigator navigator = current.getNavigator();
            navigator.navigateTo(DashboardView.getViewName());

        } catch (AuthenticationException e) {
            Notification.show("Authentication failed: "
                    + e.getMessage());
        }
    }
}
