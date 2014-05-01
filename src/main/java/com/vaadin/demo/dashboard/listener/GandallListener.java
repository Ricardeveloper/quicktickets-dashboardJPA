/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.demo.dashboard.listener;

import com.google.common.eventbus.EventBus;
import com.vaadin.demo.dashboard.DashboardUI;
import com.vaadin.demo.dashboard.event.LoginEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

/**
 * @author Muaz Cisse
 */
@Controller
public class GandallListener implements Button.ClickListener {

    private EventBus eventBus;

    private LoginEvent loginEvent;

    private ApplicationContext applicationContext;

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
