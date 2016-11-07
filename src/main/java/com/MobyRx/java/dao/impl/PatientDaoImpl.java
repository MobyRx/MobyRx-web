package com.MobyRx.java.dao.impl;

import com.MobyRx.java.dao.PatientDao;
import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.entity.DoctorProfileEntity;
import com.MobyRx.java.entity.PatientProfileEntity;
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
 * Time: 9:38 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("patientDao")
public class PatientDaoImpl extends BaseDaoImpl implements PatientDao {


	public List<PatientProfileEntity> searchPatient(Map<String, String> queryParam) {
		Criteria criteria = getCurrentSession().createCriteria(UserEntity.class);
		if (null != queryParam && queryParam.size()>0) {
			for (String filedName : queryParam.keySet()) {
				criteria.add(Restrictions.eq(filedName, queryParam.get(filedName)));
			}
		}
		List<UserEntity> userEntity =(List<UserEntity>)criteria.list();	
		Criteria docCriteria = getCurrentSession().createCriteria(ProfileEntity.class);
		docCriteria.add(Restrictions.disjunction()
				.add(Restrictions.eq("user.id", userEntity.get(0).getId())));
		List<PatientProfileEntity> patientProfileEntity=(List<PatientProfileEntity>)docCriteria.list();
	
		return patientProfileEntity;
	}

}
