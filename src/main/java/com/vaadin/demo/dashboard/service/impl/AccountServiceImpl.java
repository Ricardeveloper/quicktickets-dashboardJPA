package com.vaadin.demo.dashboard.service.impl;

import com.vaadin.demo.dashboard.dao.AccountDao;
import com.vaadin.demo.dashboard.model.Account;
import com.vaadin.demo.dashboard.service.AccountService;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
//@Transactional
//@PreAuthorize("denyAll")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    Calendar cal = GregorianCalendar.getInstance();
    Timestamp timestamp = new Timestamp(cal.getTimeInMillis());

    /**
     *
     * @param username
     * @param password
     * @return
     */
    @Transactional
    @Override
    public UserDetails getAccountByUsernameAndPassword(String username, String password) {

        Account account = accountDao.loadAccountByUsername(username);

        if (account != null) {
            if (passwordEncoder.matches(password, account.getPassword())) {
                accountDao.resetFailAttempts(account);
                return account;
            } else {
                accountDao.updateFailAttempts(account);
            }
        }
        throw new BadCredentialsException("Bad Credentials.");
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
     * @param account
     */
    @PreAuthorize("hasRole('AUTHORITY_ADMIN')")
    @Transactional(readOnly = false)
    @Override
    public void createAccount(Account account) {
        String password = account.getPassword();
        String encryptedPassword = passwordEncoder.encode(password);
        account.setPassword(encryptedPassword);
        accountDao.create(account);
    }

}
