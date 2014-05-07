package com.vaadin.demo.dashboard.service.impl;

import com.vaadin.demo.dashboard.dao.AccountDao;
import com.vaadin.demo.dashboard.model.Account;
import com.vaadin.demo.dashboard.service.AccountService;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @author Muaz Cisse
 */
@Service
@Transactional
@PreAuthorize("denyAll")
public class AccountServiceImpl implements AccountService {

    @Inject
    private AccountDao accountDao;

    @PreAuthorize("hasRole('PERM_READ_ACCOUNTS')")
    @Override
    public Account getAccountByUsername(String username) {
        return accountDao.getByUsername(username);
    }


}
