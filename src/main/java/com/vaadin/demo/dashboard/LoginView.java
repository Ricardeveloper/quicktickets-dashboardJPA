/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.demo.dashboard;

import com.vaadin.demo.dashboard.view.form.LoginForm;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 *
 * @author muaz.cisse
 */
public class LoginView extends HorizontalLayout implements View {

    public static final String NAME = "login";

    Window notifications;

    private final HelpManager helpManager;

    public LoginView() {

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

        LoginForm loginForm = new LoginForm();
        addComponent(loginForm);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
