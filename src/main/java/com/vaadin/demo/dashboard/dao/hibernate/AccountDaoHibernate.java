package com.vaadin.demo.dashboard.dao.hibernate;

import com.vaadin.demo.dashboard.dao.AccountDao;
import com.vaadin.demo.dashboard.model.Account;
import javax.transaction.Transactional;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import static org.springframework.util.Assert.notNull;

/**
 *
 * @author Muaz Cisse
 */
@Repository("accountDao")
public class AccountDaoHibernate extends AbstractDaoHibernate<Account> implements AccountDao {

    @Override
    @Transactional
    public UserDetails loadUserByUsernameAndPassword(String username, String password) throws UsernameNotFoundException, DataAccessException {
        notNull(username, "username can't be null");
        notNull(password, "password can't be null");
        return (Account) getSession()
                .getNamedQuery("account.byUsernameAndPassword")
                .setParameter("username", username)
                .setParameter("password", password)
                .uniqueResult();
    }

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        notNull(username, "username can't be null");
        return (Account) getSession()
                .getNamedQuery("account.byUsername")
                .setParameter("username", username)
                .uniqueResult();
    }

}
