package com.vaadin.demo.dashboard.controller;

import com.vaadin.demo.dashboard.view.LoginView;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author muaz.cisse
 */
public class ViewChangeSecurityChecker implements ViewChangeListener {

    public boolean isViewChangeAllowed(ViewChangeEvent event) {

        if (event.getNewView() instanceof LoginView) {

            return true;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication == null ? false : authentication.isAuthenticated();
    }

    public void navigatorViewChanged(ViewChangeEvent event) {
    }

    @Override
    public boolean beforeViewChange(ViewChangeEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        return true;
    }

    @Override
    public void afterViewChange(ViewChangeEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
