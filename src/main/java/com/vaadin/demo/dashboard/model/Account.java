package com.vaadin.demo.dashboard.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import static org.apache.commons.lang.builder.CompareToBuilder.reflectionCompare;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Muaz Cisse
 */
@Entity
@Table(name = "account")
@NamedQueries(
        {
            @NamedQuery(
                    name = "account.byUsername",
                    query = "from Account a where a.username = :username"),
            @NamedQuery(
                    name = "account.byUsernameAndPassword",
                    query = "from Account a where a.username = :username and a.password = :password")
        }
)
public class Account implements UserDetails, Serializable, Comparable<Account> {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Date expirationDate;

    @NotNull
    private boolean enabled;

    @NotNull
    private boolean accountNonLocked;

    @NotNull
    private boolean credentialsNonExpired;

    private Set<Authority> gandallAuthorities = new HashSet<>();

    private AccountLoginAttempts accountLoginAttemps;

    public Account() {
    }

    public Account(String username) {
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @SuppressWarnings("unused")
    private void setId(Long id) {
        this.id = id;
    }

    @Column(name = "username", unique = true, nullable = false)
    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Transient
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Column(name = "email", unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    @Column(name = "expiration_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     *
     * @param expirationDate
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        // subtract one day to the current date
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);

        return expirationDate.after(cal.getTime());
    }

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
     */
    @Column(name = "account_non_locked")
    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
     */
    @Column(name = "credential_non_expired")
    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
     */
    @Column(name = "enabled")
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     *
     * @return
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "account_authority",
            joinColumns = {
                @JoinColumn(name = "account_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "authority_id")})
    public Set<Authority> getGandallAuthorities() {
        return gandallAuthorities;
    }

    public void setGandallAuthorities(Set<Authority> gandallAuthorities) {
        this.gandallAuthorities = gandallAuthorities;
    }

    /**
     *
     * @return
     */
    @Transient
    public Set<Permission> getPermissions() {
        Set<Permission> perms = new HashSet<>();
        gandallAuthorities.stream().forEach((authority) -> {
            perms.addAll(authority.getPermissions());
        });
        return perms;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    @Transient
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.addAll(getGandallAuthorities());
        return authorities;
    }

    /**
     *
     * @return
     */
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "account")
    @Cascade({CascadeType.ALL})
    public AccountLoginAttempts getAccountLoginAttemps() {
        return accountLoginAttemps;
    }

    /**
     *
     * @param accountLoginAttemps
     */
    public void setAccountLoginAttemps(AccountLoginAttempts accountLoginAttemps) {
        this.accountLoginAttemps = accountLoginAttemps;
    }

    /**
     * <p>
     * Returns the username.
     * </p>
     *
     * @return
     */
    @Override
    public String toString() {
        return username;
    }

    @Override
    public int compareTo(Account account) {
        return reflectionCompare(this, account);
    }

}
