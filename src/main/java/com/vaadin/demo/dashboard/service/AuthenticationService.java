package com.vaadin.demo.dashboard.service;

import com.vaadin.demo.dashboard.event.LoginEvent;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author Muaz Cisse
 */
public interface AuthenticationService extends AuthenticationManager, Serializable {

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException;

    public void handleAuthentication(LoginEvent loginEvent, HttpServletRequest httpRequest);

    public void handleLogout(HttpServletRequest httpRequest);

    public boolean hasAnyRole(String... roles);
}
