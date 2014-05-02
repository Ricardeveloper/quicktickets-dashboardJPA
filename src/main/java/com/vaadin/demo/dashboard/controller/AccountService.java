package com.vaadin.demo.dashboard.controller;

import com.vaadin.demo.dashboard.dao.AccountDao;
import com.vaadin.demo.dashboard.model.Account;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Muaz Cisse
 */
@Service
@Transactional
@PreAuthorize("denyAll")
public class AccountService implements UserDetailsService {

    @Inject 
    private AccountDao accountDao;
    
    @PreAuthorize("hasRole('PERM_READ_ACCOUNTS')")
    public Account getAccountByUsername(String username) {
        return accountDao.getByUsername(username);
    }

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
