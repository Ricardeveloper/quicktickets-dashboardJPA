package com.vaadin.demo.dashboard.view.form;

import com.google.common.eventbus.EventBus;
import com.vaadin.demo.dashboard.DashboardUI;
import com.vaadin.demo.dashboard.listener.LoginListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

/**
 *
 * @author muaz.cisse
 */
@Controller
public class LoginForm extends HorizontalLayout {

    VerticalLayout loginLayout;

    private final TextField username = new TextField("Username");
    private final PasswordField password = new PasswordField("Password");

    public LoginForm() {
    }

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
        //LoginEvent loginEvent = new LoginEvent(username.getValue(), password.getValue());
        signin.addShortcutListener(enter);
        loginPanel.addComponent(fields);
        loginLayout.addComponent(loginPanel);
        loginLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);

        /*
         signin.addClickListener((Button.ClickEvent event) -> {

         System.out.println(username.getValue());

         //LoginListener loginListener = getLoginListener();
         //loginListener.setEventBus(eventBus);
         LoginEvent loginEvent = new LoginEvent(username.getValue(), password.getValue());
         loginListener.setLoginEvent(loginEvent);

         });

       
        
         signin.addClickListener(new ClickListener() {

         @Override
         public void buttonClick(Button.ClickEvent event) {
         System.out.println(getUsername().getValue());

         LoginEvent loginEvent = new LoginEvent(getUsername().getValue(), getPassword().getValue());

         //((DashboardUI) UI.getCurrent()).getSession().setAttribute("username", getUsername().getValue());
         //((DashboardUI) UI.getCurrent()).getSession().setAttribute("password", getPassword().getValue());
         }
         });
        
         */
        signin.addClickListener(loginListener);
    }

    private LoginListener getLoginListener() {

        DashboardUI ui = (DashboardUI) UI.getCurrent();
        ApplicationContext context = ui.getApplicationContext();

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
