/**
 * The purpose of this class is to help with the management of Vaadin controls
 * child-parent tree.
 */
package com.vaadin.demo.dashboard.util;

import com.vaadin.ui.AbstractComponent;

/**
 *
 * @author muaz.cisse
 */
public class ControlHelper {

    private static final int _MAXIMUM_PARENT_ITERATION_COUNT = 100;

    /**
     * Get parent of the control which has a specified type.
     *
     * @param cls type of the control to return
     * @param control origin control (where to start traversing the tree)
     * @param <T>
     * @return
     */
    public static <T extends AbstractComponent> T getParent(Class<T> cls, AbstractComponent control) {
        
        if (control == null) {
            return null;
        }

        AbstractComponent parent = control;

        for (int i = 0; i < _MAXIMUM_PARENT_ITERATION_COUNT; i++) {
            if (parent.getParent() == null) {
                return null;
            }

            //Return parent of the desired class type
            if (parent.getParent().getClass().isAssignableFrom(cls)) {
                return (T) parent.getParent();
            }

            parent = (AbstractComponent) parent.getParent();
        }

        return null;
    }
}
