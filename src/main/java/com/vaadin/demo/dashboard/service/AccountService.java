package com.vaadin.demo.dashboard.service;

import com.vaadin.demo.dashboard.model.Account;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Muaz Cisse
 */
public interface AccountService {

    public UserDetails getAccountByUsernameAndPassword(String username, String password);

    public UserDetails getAccountByUsername(String username);

    public void createAccount(Account account);

}
