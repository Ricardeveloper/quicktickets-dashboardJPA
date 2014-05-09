/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.demo.dashboard.service.impl;

import com.vaadin.demo.dashboard.service.InitDbService;

// https://github.com/jirkapinkas/example-mailer-vaadin-spring/blob/master/src/main/java/cz/jiripinkas/example/mailer/service/InitDbService.java
public class InitDbServiceImpl implements InitDbService {

    //@Autowired
    //private UserService userService;

    //@Autowired
    //private RoleService roleService;

    //@Autowired
    //private EmailService emailService;

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int initDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createAccount(int roleUserId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createPermission(int permissionName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createAuthority(int authorityName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void authorityHasPermission(int authorityName, int permissionName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void accountHasAuthority(int accountUsername, int authorityName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
