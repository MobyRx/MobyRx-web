package com.MobyRx.java.dao.impl;

import com.MobyRx.java.entity.BaseEntity;
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
        SQLQuery query =getCurrentSession().createSQLQuery(sqlQuery);
        query.executeUpdate();
    }

    public void save(Object object)  throws Exception{
        getCurrentSession().saveOrUpdate(object);
    }

    public void saveAll(List objectList) {
        for (Object object : objectList) {
            try{
                getCurrentSession().saveOrUpdate(object);
            }catch (JDBCException jdbce){
                logger.error(jdbce.toString());
                if(jdbce.getErrorCode()==1062 && jdbce.getSQLState().equals("23000")){
                    logger.error("Duplicate Object :- "+ object.toString());
                }else{
                    //throw jdbce;
                }
            }
        }
    }

    public void update(Object object){
        getCurrentSession().update(object);
    }

    public void delete(Object object){
        getCurrentSession().delete(object);
    }

    public void delete(Class clazz, Long id) {
        String hql = "delete from "+ clazz.getName() + " where id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }


    
    public <T extends BaseEntity> T get( Class<T> clazz, Long id){
        return (T)getCurrentSession().load(clazz, id);
    }

    public <T extends BaseEntity> List get( Class<T> clazz,String associatedProperty, Long id){
        Criteria criteria = getCurrentSession().createCriteria(clazz)
                .createAlias(associatedProperty, "prop")
                .add(Restrictions.eq("prop.id", id));
        return criteria.list();
    }


    protected Session getCurrentSession() {
        Session session = null;
        try{
            session = sessionFactory.getCurrentSession();
        }catch (HibernateException he){
            session = sessionFactory.openSession();
        }
        if(null == session)
            session = sessionFactory.openSession();
        return session;
    }
}
