package com.vaadin.demo.dashboard.controller;

import java.io.Serializable;
import java.util.Collection;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author muaz.cisse
 */
@Component
public class AuthManager implements AuthenticationManager, Serializable {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        String username = (String) auth.getPrincipal();
        String password = (String) auth.getCredentials();
        UserDetails user = userService.loadUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        }
        throw new BadCredentialsException("Bad Credentials");
    }

    public void handleAuthentication(String login, String password, HttpServletRequest httpRequest) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("Muaz", "1234");

        token.setDetails(new WebAuthenticationDetails(httpRequest));

        Authentication authentication = authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    public void handleLogout(HttpServletRequest httpRequest) {

        ServletContext servletContext = httpRequest.getSession().getServletContext();

        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

        LogoutHandler logoutHandler = wac.getBean(LogoutHandler.class);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Response should not be used?
        logoutHandler.logout(httpRequest, null, authentication);
    }
}
