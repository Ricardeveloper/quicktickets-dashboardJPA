package com.vaadin.demo.dashboard.view;

import com.google.common.eventbus.EventBus;
import com.vaadin.demo.dashboard.DashboardUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

/**
 * @author muaz.cisse
 */
@Controller
public class GandallView extends HorizontalLayout implements View {

    private static String viewName;

    private EventBus eventBus;

    private ApplicationContext applicationContext;

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

    public ApplicationContext getApplicationContext() {

        DashboardUI ui = (DashboardUI) UI.getCurrent();
        this.applicationContext = ui.getApplicationContext();

        return this.applicationContext;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
