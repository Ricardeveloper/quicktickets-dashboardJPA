package com.vaadin.demo.dashboard.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by muazcisse on 11.05.14.
 */
public abstract class GandallEventListener implements InitializingBean {

    Log log = LogFactory.getLog(this.getClass());

    @Autowired
    EventDispatcher eventDispatcher;

    /**
     * Implementation of this method checks whether the given event can
     * be handled in this class. This method will be called by the event
     * dispatcher.
     *
     * @param event the event to handle
     * @return true if the implementing subclass can handle the event
     */
    public abstract boolean canHandle(Object event);

    /**
     * This method is executed by the event dispatcher with the
     * event object.
     *
     * @param event the event to handle
     */
    public abstract void handle(Object event);

    /**
     * Invoked by a BeanFactory after it has set all bean properties supplied
     * (and satisfied BeanFactoryAware and ApplicationContextAware).
     * <p>This method allows the bean instance to perform initialization only
     * possible when all bean properties have been set and to throw an
     * exception in the event of misconfiguration.
     *
     * @throws Exception in the event of misconfiguration (such
     *                   as failure to set an essential property) or if initialization fails.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        eventDispatcher.registerListener(this);
    }

}
