package com.vaadin.demo.dashboard.model;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static org.apache.commons.lang.builder.CompareToBuilder.reflectionCompare;

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

    @NotEmpty
    private String username;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private Date expirationDate;

    @NotEmpty
    private boolean enabled;

    @NotEmpty
    private boolean accountNonLocked;

    @NotEmpty
    private boolean credentialsNonExpired;

    private Set<Authority> gandallAuthorities = new HashSet<>();

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

    @Column(name = "username")
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

    @Column(name = "email")
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

    @Column(name = "expiration_date")
    public Date getExpirationDate() {
        return expirationDate;
    }

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
        //authorities.addAll(getPermissions());
        return authorities;
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
