package com.vaadin.demo.dashboard.dao.hibernate;

import com.vaadin.demo.dashboard.dao.AccountDao;
import com.vaadin.demo.dashboard.model.Account;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import static org.springframework.util.Assert.notNull;

/**
 *
 * @author Muaz Cisse
 */
@Repository("accountDao")
public class AccountDaoHibernate extends AbstractDaoHibernate<Account> implements AccountDao {

    /* (non-Javadoc)
     * @see com.springinpractice.ch07.dao.AccountDao#getByUsername(java.lang.String)
     */
    @Override
    public Account getByUsername(String username) {
        notNull(username, "username can't be null");
        return (Account) getSession()
                .getNamedQuery("account.byUsername")
                .setParameter("username", username)
                .uniqueResult();
    }

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        notNull(username, "username can't be null");
        Account account = getByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("No user with username " + username);
        }
        return account;
    }
}
