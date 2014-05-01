package com.vaadin.demo.dashboard.controller;

import com.vaadin.server.VaadinServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author muaz.cisse
 */
public class RequestHolderApplicationServlet extends VaadinServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SecurityContextHolder.setContext(SecurityContextHolder.createEmptyContext());

        RequestHolder.setRequest(request);

        super.service(request, response);

        // We remove the request from the thread local, there's no reason to keep it once the work is done
        RequestHolder.clean();

        SecurityContextHolder.clearContext();
    }
}
