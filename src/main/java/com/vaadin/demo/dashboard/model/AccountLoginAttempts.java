package com.vaadin.demo.dashboard.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import static org.apache.commons.lang.builder.CompareToBuilder.reflectionCompare;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author Muaz Cisse
 */
@Entity
@Table(name = "account_attempts")
@NamedQueries(
        {
            @NamedQuery(name = "account.updateLoginAttemps",
                    query = AccountLoginAttempts.SQL_ACCOUNT_ATTEMPTS_UPDATE_ATTEMPTS)
        }
)
public class AccountLoginAttempts implements Serializable, Comparable<AccountLoginAttempts> {

    protected static final String SQL_ACCOUNT_ATTEMPTS_UPDATE_ATTEMPTS = "UPDATE AccountLoginAttempts al SET al.attempts = :attempts, al.lastModified = :lastModified WHERE al.id = :id";

    private static final long serialVersionUID = 1L;

    private Long account_id;

    private int attempts;

    private Date lastModified;

    private Account account;

    /**
     *
     * @return
     */
    @Id
    @Column(name = "account_id")
    public Long getId() {
        return account_id;
    }

    @SuppressWarnings("unused")
    public void setId(Long id) {
        this.account_id = id;
    }

    /**
     * @return the attempts
     */
    @Column(name = "attempts")
    public int getAttempts() {
        return attempts;
    }

    /**
     * @param attempts the attempts to set
     */
    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    /**
     * @return the lastModified
     */
    @Column(name = "lastModified")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastModified() {
        return lastModified;
    }

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * @return the account
     */
    @OneToOne
    @PrimaryKeyJoinColumn
    public Account getAccount() {
        return account;
    }

    /**
     * @param account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * @param attemps
     */
    @Override
    public int compareTo(AccountLoginAttempts attemps) {
        return reflectionCompare(this, attemps);
    }
}
