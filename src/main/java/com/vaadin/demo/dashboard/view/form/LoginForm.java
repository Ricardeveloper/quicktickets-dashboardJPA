/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.demo.dashboard.view.form;

import com.google.common.eventbus.EventBus;
import com.vaadin.demo.dashboard.DashboardUI;
import com.vaadin.demo.dashboard.listener.LoginListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.*;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author muaz.cisse
 */
public class LoginForm extends HorizontalLayout {

    private final TextField username = new TextField("Username");
    private final PasswordField password = new PasswordField("Password");
    VerticalLayout loginLayout;

    public LoginForm(final EventBus eventBus) {

        loginLayout = new VerticalLayout();
        loginLayout.setSizeFull();
        loginLayout.addStyleName("login-layout");

        UI.getCurrent().setContent(loginLayout);

        final CssLayout loginPanel = new CssLayout();
        loginPanel.addStyleName("login-panel");

        HorizontalLayout labels = new HorizontalLayout();
        labels.setWidth("100%");
        labels.setMargin(true);
        labels.addStyleName("labels");
        loginPanel.addComponent(labels);

        Label welcome = new Label("Welcome");
        welcome.setSizeUndefined();
        welcome.addStyleName("h4");
        labels.addComponent(welcome);
        labels.setComponentAlignment(welcome, Alignment.MIDDLE_LEFT);

        Label title = new Label("QuickTickets Dashboard");
        title.setSizeUndefined();
        title.addStyleName("h2");
        title.addStyleName("light");
        labels.addComponent(title);
        labels.setComponentAlignment(title, Alignment.MIDDLE_RIGHT);

        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);
        fields.setMargin(true);
        fields.addStyleName("fields");

        //username.setValue("TEST");
        //username.setNullRepresentation("");
        username.focus();
        fields.addComponent(username);

        password.setNullRepresentation("");
        fields.addComponent(password);

        final Button signin = new Button("Sign In");
        signin.addStyleName("default");
        fields.addComponent(signin);
        fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

        final ShortcutListener enter = new ShortcutListener("Sign In",
                ShortcutAction.KeyCode.ENTER, null) {
                    @Override
                    public void handleAction(Object sender, Object target) {
                        signin.click();
                    }
                };

        LoginListener loginListener = getLoginListener();

        loginListener.setEventBus(eventBus);
        signin.addClickListener(loginListener);

        signin.addShortcutListener(enter);
        loginPanel.addComponent(fields);
        loginLayout.addComponent(loginPanel);
        loginLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
    }

    private LoginListener getLoginListener() {

        DashboardUI ui = (DashboardUI) UI.getCurrent();
        ApplicationContext context = ui.getApplicationContext();

        ((DashboardUI) UI.getCurrent()).getSession().setAttribute("username", getUsername().getValue());
        ((DashboardUI) UI.getCurrent()).getSession().setAttribute("password", getPassword().getValue());

        return context.getBean(LoginListener.class);
    }

    /**
     * @return the username
     */
    public TextField getUsername() {
        return username;
    }

    /**
     * @return the password
     */
    public PasswordField getPassword() {
        return password;
    }

}
