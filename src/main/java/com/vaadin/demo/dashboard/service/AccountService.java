package com.vaadin.demo.dashboard.service;

import com.vaadin.demo.dashboard.model.Account;

/**
 * @author Muaz Cisse
 */
public interface AccountService {

    public Account getAccountByUsername(String username);

}
