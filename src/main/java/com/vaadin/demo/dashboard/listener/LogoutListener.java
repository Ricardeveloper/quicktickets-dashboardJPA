package com.vaadin.demo.dashboard.listener;

import com.google.common.eventbus.Subscribe;
import com.vaadin.demo.dashboard.DashboardUI;
import com.vaadin.demo.dashboard.controller.AuthManager;
import com.vaadin.demo.dashboard.controller.RequestHolder;
import com.vaadin.demo.dashboard.event.LogoutEvent;
import com.vaadin.demo.dashboard.view.LoginView;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author muaz.cisse
 */
@Controller
public class LogoutListener extends GandallListener {

    @Autowired
    private final AuthManager authManager = new AuthManager();

    @Subscribe
    @Override
    public void buttonClick(Button.ClickEvent event) {

        authManager.handleLogout(RequestHolder.getRequest());

        getEventBus().post(new LogoutEvent());

        DashboardUI current = (DashboardUI) UI.getCurrent();
        Navigator navigator = current.getNavigator();
        navigator.navigateTo(LoginView.getViewName());

    }
}
