package com.vaadin.demo.dashboard.service.impl;

import com.vaadin.demo.dashboard.dao.AccountDao;
import com.vaadin.demo.dashboard.model.Account;
import com.vaadin.demo.dashboard.service.AccountService;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Muaz Cisse
 */
@Service
@Transactional
//@PreAuthorize("denyAll")
public class AccountServiceImpl implements AccountService {

    @Inject
    private AccountDao accountDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public UserDetails getAccountByUsernameAndPassword(String username, String password) {

        UserDetails account = accountDao.loadUserByUsername(username);

        if (account != null && passwordEncoder.matches(password, account.getPassword())) {
            return account;
        }
        throw new BadCredentialsException("Bad Credentials");
    }

    /**
     *
     * @param username
     * @return
     */
    @PreAuthorize("hasRole('AUTHORITY_USER')")
    @Transactional(readOnly = true)
    @Override
    public UserDetails getAccountByUsername(String username) {
        return accountDao.loadUserByUsername(username);
    }

    /**
     *
     * @param user
     */
    @PreAuthorize("hasRole('AUTHORITY_ADMIN')")
    @Override
    public void createAccount(Account user) {
        String password = user.getPassword();
        String encryptedPassword = passwordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        accountDao.create(user);
    }

}
