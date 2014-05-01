package com.vaadin.demo.dashboard.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Muaz Cisse
 */
public class Main {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:**/ccApp-config.xml");

        TestBean testBean;
        testBean = (TestBean) ctx.getBean("testBean");

        testBean.run();

    }
}
