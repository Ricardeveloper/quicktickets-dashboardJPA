package com.vaadin.demo.dashboard.event;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author Muaz Cisse
 */
@Controller
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class LoginEvent {

    private String login;
    private String password;

    public LoginEvent() {

    }

    public LoginEvent(String login, String password) {

        this.login = login;
        this.password = password;
    }

    public String getLogin() {

        return login;
    }

    public String getPassword() {

        return password;
    }
}
