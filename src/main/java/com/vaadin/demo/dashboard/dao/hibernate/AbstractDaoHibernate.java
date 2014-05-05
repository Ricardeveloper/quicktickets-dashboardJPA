package com.vaadin.demo.dashboard.dao.hibernate;

import com.vaadin.demo.dashboard.dao.Dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

/**
 * @param <T>
 * @author Muaz Cisse
 */
public abstract class AbstractDaoHibernate<T extends Object> implements Dao<T> {

    //@Autowired
    private SessionFactory sessionFactory;
    private Class<T> domainClass;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    private Class<T> getDomainClass() {
        if (domainClass == null) {
            ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
            this.domainClass = (Class<T>) thisType.getActualTypeArguments()[0];
        }
        return domainClass;
    }

    private String getDomainClassName() {
        return getDomainClass().getName();
    }

    /* (non-Javadoc)
     * @see com.springinpractice.dao.Dao#create(java.lang.Object)
     */
    @Override
    public void create(T t) {

        // If there's a setDateCreated() method, then set the date.
        Method method = ReflectionUtils.findMethod(
                getDomainClass(), "setDateCreated", new Class[]{Date.class});
        if (method != null) {
            try {
                method.invoke(t, new Date());
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                // Ignore any exception here; simply abort the setDate() attempt
            }
        }

        getSession().save(t);
    }

    /* (non-Javadoc)
     * @see com.springinpractice.dao.Dao#get(java.io.Serializable)
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(Serializable id) {
        return (T) getSession().get(getDomainClass(), id);
    }

    /* (non-Javadoc)
     * @see com.springinpractice.dao.Dao#load(java.io.Serializable)
     */
    @Override
    @SuppressWarnings("unchecked")
    public T load(Serializable id) {
        return (T) getSession().load(getDomainClass(), id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        return getSession()
                .createQuery("from " + getDomainClassName())
                .list();
    }

    /* (non-Javadoc)
     * @see com.springinpractice.dao.Dao#update(java.lang.Object)
     */
    @Override
    public void update(T t) {
        getSession().update(t);
    }

    /* (non-Javadoc)
     * @see com.springinpractice.dao.Dao#delete(java.lang.Object)
     */
    @Override
    public void delete(T t) {
        getSession().delete(t);
    }

    /* (non-Javadoc)
     * @see com.springinpractice.dao.Dao#deleteById(java.io.Serializable)
     */
    @Override
    public void deleteById(Serializable id) {
        delete(load(id));
    }

    /* (non-Javadoc)
     * @see com.springinpractice.dao.Dao#deleteAll()
     */
    @Override
    public void deleteAll() {
        getSession()
                .createQuery("delete " + getDomainClassName())
                .executeUpdate();
    }

    /* (non-Javadoc)
     * @see com.springinpractice.dao.Dao#count()
     */
    @Override
    public long count() {
        return (Long) getSession()
                .createQuery("select count(*) from " + getDomainClassName())
                .uniqueResult();
    }

    /* (non-Javadoc)
     * @see com.springinpractice.dao.Dao#exists(java.io.Serializable)
     */
    @Override
    public boolean exists(Serializable id) {
        return (get(id) != null);
    }
}
