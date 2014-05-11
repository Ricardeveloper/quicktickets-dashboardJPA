package com.vaadin.demo.dashboard.service.impl;

import com.vaadin.demo.dashboard.controller.AccountStatusChecker;
import com.vaadin.demo.dashboard.event.LoginEvent;
import com.vaadin.demo.dashboard.listener.EventDispatcher;
import com.vaadin.demo.dashboard.service.AccountService;
import com.vaadin.demo.dashboard.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author Muaz Cisse
 */
@Controller
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private EventDispatcher authenticationListener;

    @Autowired
    private AccountStatusChecker accountStatusChecker;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        //EventBus eventBus = new EventBus();

        String username = (String) auth.getPrincipal();
        String password = (String) auth.getCredentials();
        UserDetails user = accountService.getAccountByUsernameAndPassword(username, password);

        //eventBus.register(authenticationListener);
        //eventBus.post(this);

        accountStatusChecker.check(user);

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), authorities);

    }

    @Override
    public void handleAuthentication(LoginEvent loginEvent, HttpServletRequest httpRequest) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginEvent.getLogin(), loginEvent.getPassword());

        token.setDetails(new WebAuthenticationDetails(httpRequest));

        Authentication authentication = authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    @Override
    public void handleLogout(HttpServletRequest httpRequest) {

        ServletContext servletContext = httpRequest.getSession().getServletContext();

        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

        LogoutHandler logoutHandler = wac.getBean(LogoutHandler.class);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Response should not be used?
        logoutHandler.logout(httpRequest, null, authentication);
    }

    @Override
    public boolean hasAnyRole(String... roles) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            for (String role : roles) {
                if (role.equals(authority.getAuthority())) {
                    return true;
                }
            }
        }

        return false;
    }
}
