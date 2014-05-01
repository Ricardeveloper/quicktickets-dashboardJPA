package com.vaadin.demo.dashboard.model;

import org.springframework.stereotype.Repository;

/**
 * @author muaz.cisse
 */
@Repository
public class User {

    private String name;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
