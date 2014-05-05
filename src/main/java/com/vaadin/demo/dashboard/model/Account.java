package com.vaadin.demo.dashboard.model;

import org.hibernate.annotations.NamedQuery;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Muaz Cisse
 */
@Entity
@Table(name = "account")
@NamedQuery(
        name = "account.byUsername",
        query = "from Account a where a.username = :username")
public class Account implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    public static final Account ACCOUNT = new Account("anonymous");

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean enabled;
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

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
     */
    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
     */
    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
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

}
