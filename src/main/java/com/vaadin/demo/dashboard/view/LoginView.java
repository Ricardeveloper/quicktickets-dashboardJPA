package com.vaadin.demo.dashboard.view;

import com.google.common.eventbus.EventBus;
import com.vaadin.demo.dashboard.HelpManager;
import com.vaadin.demo.dashboard.HelpOverlay;
import com.vaadin.demo.dashboard.view.form.LoginForm;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 *
 * @author muaz.cisse
 */
public class LoginView extends GandallView {

    Window notifications;

    private final HelpManager helpManager;

    public LoginView(final EventBus eventBus) {

        setViewName("login");
        
        setEventBus(eventBus);
        
        notifications = new Window("HelpManager");

        helpManager = new HelpManager(notifications.getUI());
        helpManager.closeAll();
        HelpOverlay w = helpManager
                .addOverlay(
                        "Welcome to the Dashboard Demo Application",
                        "<p>This application is not real, it only demonstrates an application built with the <a href=\"http://vaadin.com\">Vaadin framework</a>.</p><p>No username or password is required, just click the ‘Sign In’ button to continue. You can try out a random username and password, though.</p>",
                        "login");
        w.center();

        UI.getCurrent().addWindow(w);

        addStyleName("login");

        LoginForm loginForm = new LoginForm(getEventBus());
        addComponent(loginForm);

    }

}
