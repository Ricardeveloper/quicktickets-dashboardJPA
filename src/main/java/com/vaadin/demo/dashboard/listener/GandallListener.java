/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.demo.dashboard.listener;

import com.google.common.eventbus.EventBus;
import com.vaadin.demo.dashboard.event.LoginEvent;
import com.vaadin.ui.Button;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Muaz Cisse
 */
@Controller
public class GandallListener implements Button.ClickListener {

    private EventBus eventBus;
    private LoginEvent loginEvent;

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

}
