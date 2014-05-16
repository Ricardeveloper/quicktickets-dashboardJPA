/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.demo.dashboard.dao;

import com.vaadin.demo.dashboard.model.Account;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Muaz Cisse
 */
public interface AccountDao extends Dao<Account>, UserDetailsService {

    Account loadAccountByUsernameAndPassword(String username, String password) throws UsernameNotFoundException, DataAccessException;

    Account loadAccountByUsername(String username) throws UsernameNotFoundException, DataAccessException;
    
    public void updateFailAttempts(Account account);

    public void resetFailAttempts(Account account);
}
