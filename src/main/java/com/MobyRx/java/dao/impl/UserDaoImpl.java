package com.MobyRx.java.dao.impl;

import com.MobyRx.java.dao.UserDao;
import com.MobyRx.java.entity.common.UserEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.MobyRx.java.entity.master.RoleEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/8/16
 * Time: 8:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {


    public UserEntity getUserByUsername(String username) {
        Criteria criteria = getCurrentSession().createCriteria(UserEntity.class)
                .add(Restrictions.or(Restrictions.eq("email", username.trim()),
                        Restrictions.eq("mobile", username)));
        return (UserEntity)criteria.uniqueResult();
    }
    
  
    public UserEntity searchUser(Map<String, Object> fieldParam, String query){

        Criteria criteria = getCurrentSession().createCriteria(UserEntity.class);
        if (null != fieldParam) {
            for (String filedName : fieldParam.keySet()) {
                criteria.add(Restrictions.eq(filedName, fieldParam.get(filedName)));
            }
        }
        criteria.add(Restrictions.disjunction()
                .add(Restrictions.eq("mobile", query.trim())));
        return (UserEntity)criteria.uniqueResult();
    
    }

    @Override
    public List<RoleEntity> getRoles(Set<String> roleNames) {
        Criteria criteria = getCurrentSession().createCriteria(RoleEntity.class)
                .add(Restrictions.in("name", roleNames));
        return criteria.list();
    }

    @Override
    public RoleEntity getRole(String name) {
        Criteria criteria = getCurrentSession().createCriteria(RoleEntity.class)
                .add(Restrictions.eq("name", name));
        return (RoleEntity)criteria.uniqueResult();
    }

}
