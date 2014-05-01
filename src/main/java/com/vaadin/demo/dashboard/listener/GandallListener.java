/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.demo.dashboard.listener;

import com.google.common.eventbus.EventBus;
import com.vaadin.ui.Button;

/**
 *
 * @author Muaz Cisse
 */
public class GandallListener implements Button.ClickListener {

    private EventBus eventBus;

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

}
