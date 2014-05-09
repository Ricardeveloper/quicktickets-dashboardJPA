/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.demo.dashboard.dao;

import com.vaadin.demo.dashboard.model.Account;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Muaz Cisse
 */
public interface AccountDao extends Dao<Account>, UserDetailsService {

    UserDetails loadUserByUsernameAndPassword(String username, String password) throws UsernameNotFoundException, DataAccessException;
}
