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

    public void updateAccount(Account account);

    public void deleteAccount(Account account);
    
    public void unblockAccount(Account account);

    public void changeAccountPassword(Account account);

    public void disableAccount(Account account);

}
