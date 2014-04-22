/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.demo.dashboard.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author muaz.cisse
 */
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<>();
        // fetch user from e.g. DB
        if ("client".equals(username)) {
            authorities.add(new SimpleGrantedAuthority("CLIENT"));
            User user = new User(username, "pass", true, true, false, false, authorities);

            return user;
        }
        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
            User user = new User(username, "pass", true, true, false, false, authorities);

            return user;

        } else {
            return null;
        }
    }
}
