/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.demo.dashboard.dao;

import com.vaadin.demo.dashboard.model.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Muaz Cisse
 */
public interface AccountDao extends Dao<Account>, UserDetailsService {

    Account getByUsername(String username);
}
