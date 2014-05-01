package com.vaadin.demo.dashboard.view;

import com.google.common.eventbus.EventBus;
import com.vaadin.demo.dashboard.DashboardUI;
import com.vaadin.demo.dashboard.HelpManager;
import com.vaadin.demo.dashboard.HelpOverlay;
import com.vaadin.demo.dashboard.view.form.LoginForm;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

/**
 *
 * @author muaz.cisse
 */
@Controller
public class LoginView extends GandallView {

    Window notifications;

    private HelpManager helpManager;

    public LoginView() {
    }

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

        DashboardUI ui = (DashboardUI) UI.getCurrent();
        ApplicationContext context = ui.getApplicationContext();

        LoginForm loginForm = (LoginForm) context.getBean("loginForm", new Object[]{getEventBus()});

        //LoginForm loginForm = new LoginForm(getEventBus());
        addComponent(loginForm);

    }

}
