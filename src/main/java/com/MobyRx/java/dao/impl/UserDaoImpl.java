package com.MobyRx.java.dao.impl;

import com.MobyRx.java.dao.UserDao;
import com.MobyRx.java.dao.impl.*;
import com.MobyRx.java.entity.BaseEntity;
import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.entity.PatientProfileEntity;
import com.MobyRx.java.entity.UserEntity;

import java.util.List;

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
    
  
    List<DrugsEntity> searchDrugs(String query) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
