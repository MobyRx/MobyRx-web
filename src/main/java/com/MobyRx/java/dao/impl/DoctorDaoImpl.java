package com.MobyRx.java.dao.impl;

import com.MobyRx.java.dao.DoctorDao;
import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.entity.DoctorProfileEntity;
import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.entity.ProfileEntity;
import com.MobyRx.java.entity.UserEntity;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/7/16
 * Time: 9:39 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("doctorDao")
public class DoctorDaoImpl extends BaseDaoImpl implements DoctorDao {

	public List<DoctorProfileEntity> searchDoctor(Map<String, Object> param, String query){

		Criteria criteria = getCurrentSession().createCriteria(UserEntity.class);
		if (null != param) {
			for (String filedName : param.keySet()) {
				criteria.add(Restrictions.eq(filedName, param.get(filedName)));
			}
		}
		criteria.add(Restrictions.disjunction()
				.add(Restrictions.eq("mobile", query.trim())));
		List<UserEntity> userEntity =(List<UserEntity>)criteria.list();
		
		//List<DoctorProfileEntity> doctorProfileEntity=null;
		Criteria docCriteria = getCurrentSession().createCriteria(ProfileEntity.class);
		docCriteria.add(Restrictions.disjunction()
				.add(Restrictions.eq("user.id", userEntity.get(0).getId())));
		List<DoctorProfileEntity> doctorProfileEntity=(List<DoctorProfileEntity>)docCriteria.list();
		/*if(userEntity!=null)
		{
			try {
				doctorProfileEntity = getSQLQuery("select * from profile where user_id="+userEntity.get(0).getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		return doctorProfileEntity;

	}


}
