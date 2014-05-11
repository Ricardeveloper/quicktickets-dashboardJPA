package com.vaadin.demo.dashboard.service;

/**
 * @author Muaz Cisse
 */
public interface InitDbService {

    public void init();

    int initDatabase();

    void createPermission(int permissionName);

    void createAuthority(int authorityName);

    void authorityHasPermission(int authorityName, int permissionName);

    void createAccount(int accountId);

    void accountHasAuthority(int accountUsername, int authorityName);


}
