package com.vaadin.demo.dashboard.dao.hibernate;

import com.vaadin.demo.dashboard.dao.AccountDao;
import com.vaadin.demo.dashboard.model.Account;
import com.vaadin.demo.dashboard.model.AccountLoginAttempts;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Qualifier;
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
@Qualifier(value = "accountDao")
public class AccountDaoHibernate extends AbstractDaoHibernate<Account> implements AccountDao {

    private static final int MAX_LOGIN_ATTEMPTS = 3;

    Calendar cal = GregorianCalendar.getInstance();
    Timestamp timestamp = new Timestamp(cal.getTimeInMillis());

    @Override
    public Account loadAccountByUsernameAndPassword(String username, String password) throws UsernameNotFoundException, DataAccessException {
        notNull(username, "username can't be null");
        notNull(password, "password can't be null");
        return (Account) getSession()
                .getNamedQuery("account.byUsernameAndPassword")
                .setParameter("username", username)
                .setParameter("password", password)
                .uniqueResult();
    }

    @Override
    public Account loadAccountByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        notNull(username, "username can't be null");
        getSession().beginTransaction();

        Account account = (Account) getSession()
                .getNamedQuery("account.byUsername")
                .setParameter("username", username)
                .uniqueResult();
        getSession().getTransaction().commit();

        return account;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        return loadAccountByUsername(username);
    }

    @Override
    public void updateFailAttempts(Account account) {
        notNull(account, "account can't be null");
        getSession().beginTransaction();

        if (account.getAccountLoginAttemps() != null) {
            // lock account if max login attemps is reached
            if (account.getAccountLoginAttemps().getAttempts() >= MAX_LOGIN_ATTEMPTS) {
                account.setAccountNonLocked(false);
                update(account);
            } else {
                // update attempts count, +1
                getSession().getNamedQuery("account.updateLoginAttemps")
                        .setInteger("attempts", account.getAccountLoginAttemps().getAttempts() + 1)
                        .setTimestamp("lastModified", timestamp)
                        .setLong("id", account.getId())
                        .executeUpdate();
            }
        } else {
            // if no login attemps record, insert a new
            AccountLoginAttempts accountLoginAttempts = new AccountLoginAttempts();
            accountLoginAttempts.setId(account.getId());
            accountLoginAttempts.setLastModified(timestamp);
            accountLoginAttempts.setAttempts(1);
            accountLoginAttempts.setAccount(account);

            account.setAccountLoginAttemps(accountLoginAttempts);

            update(account);
        }

        getSession().getTransaction().commit();
    }

    @Override
    public void resetFailAttempts(Account account) {
        notNull(account, "account can't be null");
        getSession().beginTransaction();
        Query query = getSession().getNamedQuery("account.updateLoginAttemps")
                .setInteger("attempts", 0)
                .setTimestamp("lastModified", timestamp)
                .setLong("id", account.getId());

        query.executeUpdate();

        /*
         account.getAccountLoginAttemps().setAttempts(0);
         account.getAccountLoginAttemps().setLastModified(timestamp);
         accountDao.update(account);
         */
        getSession().getTransaction().commit();
    }

    @Override
    public void unblockAccount(Account account) {
        getSession().beginTransaction();
        getSession().getTransaction().commit();
    }

    @Override
    public void changeAccountPassword(Account account) {
        getSession().beginTransaction();
        getSession().getTransaction().commit();
    }

    @Override
    public void disableAccount(Account account) {
        getSession().beginTransaction();
        getSession().getTransaction().commit();
    }

}
