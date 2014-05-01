package com.vaadin.demo.dashboard.view;

import com.google.common.eventbus.EventBus;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalLayout;
import org.springframework.stereotype.Controller;

/**
 *
 * @author muaz.cisse
 */
@Controller
public class GandallView extends HorizontalLayout implements View {

    private static String viewName;

    private EventBus eventBus;

    public GandallView() {

    }

    /**
     * @return the viewName
     */
    public static String getViewName() {
        return viewName;
    }

    /**
     * @param name the viewName to set
     */
    public static void setViewName(String name) {
        viewName = name;
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
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
