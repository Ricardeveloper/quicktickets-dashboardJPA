package com.vaadin.demo.dashboard.listener;

import com.google.common.eventbus.EventBus;
import com.vaadin.demo.dashboard.DashboardUI;
import com.vaadin.demo.dashboard.event.LoginEvent;
import com.vaadin.demo.dashboard.model.Account;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.io.Serializable;

/**
 * @author Muaz Cisse
 */
@Controller
public abstract class GandallListener implements Button.ClickListener, Serializable {

    private EventBus eventBus;

    private LoginEvent loginEvent;

    private ApplicationContext applicationContext;

    public static Account getCurrentAccount() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return ((Account) authentication.getPrincipal());
    }

    /**
     * @return the eventBus
     */
    public EventBus getEventBus() {
        return eventBus;
    }

    /**
     * @param eventBus the eventBus to set
     */
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {

    }

    /**
     * @return the loginEvent
     */
    public LoginEvent getLoginEvent() {
        return loginEvent;
    }

    /**
     * @param loginEvent the loginEvent to set
     */
    public void setLoginEvent(LoginEvent loginEvent) {
        this.loginEvent = loginEvent;
    }

    public ApplicationContext getApplicationContext() {

        DashboardUI ui = (DashboardUI) UI.getCurrent();
        this.applicationContext = ui.getApplicationContext();

        return this.applicationContext;
    }

}
