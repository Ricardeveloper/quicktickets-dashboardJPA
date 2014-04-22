/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.demo.dashboard;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author muaz.cisse
 */
public class LoginView extends VerticalLayout implements View {

    public LoginView() {
        LoginForm loginForm = new LoginForm();
        addComponent(loginForm);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
