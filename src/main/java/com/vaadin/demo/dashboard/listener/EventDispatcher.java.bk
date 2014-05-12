package com.vaadin.demo.dashboard.listener;

import com.google.common.eventbus.Subscribe;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muaz Cisse on 10.05.14.
 */
@Controller
public class EventDispatcher implements ApplicationListener {

    List<GandallEventListener> listeners = new ArrayList<>();

    /**
     * Method that allows registering of an Event Listener.
     *
     * @param listener
     */
    public void registerListener(GandallEventListener listener) {
        listeners.add(listener);
    }

    /**
     * Handle an application event.
     *
     * @param appEvent he event to respond to
     */
    @Subscribe
    public void onApplicationEvent(AbstractAuthenticationEvent appEvent) {

        if (appEvent instanceof AuthenticationSuccessEvent) {
            AuthenticationSuccessEvent event = (AuthenticationSuccessEvent) appEvent;
        }

        if (appEvent instanceof AuthenticationFailureBadCredentialsEvent) {
            AuthenticationFailureBadCredentialsEvent event = (AuthenticationFailureBadCredentialsEvent) appEvent;
        }

        listeners.stream().filter(listener -> listener.canHandle(appEvent)).forEach(listener -> {
            listener.handle(appEvent);
        });

    }

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        listeners.stream().filter(listener -> listener.canHandle(event)).forEach(listener -> {
            listener.handle(event);
        });
    }
}
