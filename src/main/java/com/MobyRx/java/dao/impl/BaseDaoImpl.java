package com.MobyRx.java.dao.impl;

import com.MobyRx.java.entity.type.Status;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/5/16
 * Time: 6:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaseDaoImpl implements com.MobyRx.java.dao.BaseDao {

    private Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void executeSQLQueryUpdate(String sqlQuery) {
        SQLQuery query = getCurrentSession().createSQLQuery(sqlQuery);
        query.executeUpdate();
    }

    public List getSQLQuery(String sqlQuery) throws Exception {
        SQLQuery query = getCurrentSession().createSQLQuery(sqlQuery);
        List<Object> list = query.list();
        return list;

    }

    public void save(Object object) throws Exception {
        try {
            getCurrentSession().saveOrUpdate(object);
        } catch (Exception Ex) {
            logger.error(Ex.getMessage());
        }
    }

    public void saveAll(List objectList) {
        for (Object object : objectList) {
            try {
                getCurrentSession().saveOrUpdate(object);
            } catch (JDBCException jdbce) {
                logger.error(jdbce.toString());
                if (jdbce.getErrorCode() == 1062 && jdbce.getSQLState().equals("23000")) {
                    logger.error("Duplicate Object :- " + object.toString());
                } else {
                    //throw jdbce;
                }
            }
        }
    }

    public void update(Object object) {
        getCurrentSession().update(object);
    }

    public void delete(Object object) {
        getCurrentSession().delete(object);
    }

    public void delete(Class clazz, Long id) {
        String hql = "delete from " + clazz.getName() + " where id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }
    
    public void softDelete(Class clazz, Long id){
        String hql = "update DoctorProfileEntity d set d.status =:status where d.id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("status", Status.DELETED);
        query.executeUpdate();
    }
        


    public <T extends Object> T get(Class<T> clazz, Long id) {
        return (T) getCurrentSession().load(clazz, id);
    }


    public <T extends Object> List get(Class<T> clazz, String associatedProperty, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(clazz)
                .createAlias(associatedProperty, "prop")
                .add(Restrictions.eq("prop.id", id));
        return criteria.list();
    }


    public Session getCurrentSession() {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException he) {
            session = sessionFactory.openSession();
        }
        if (null == session)
            session = sessionFactory.openSession();
        return session;
    }


}
