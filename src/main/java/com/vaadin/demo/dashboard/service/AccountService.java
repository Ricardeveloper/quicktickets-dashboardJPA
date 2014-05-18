package com.vaadin.demo.dashboard.service;

import com.vaadin.demo.dashboard.model.Account;
import com.vaadin.demo.dashboard.model.Authority;
import com.vaadin.demo.dashboard.model.Permission;
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

    public void createAuthority(Authority authority);

    public void updateAuthority(Authority authority);

    public void deleteAuthority(Authority authority);

    public void createPermission(Permission permission);

    public void updatePermission(Permission permission);

    public void deletePermission(Permission permission);

}
