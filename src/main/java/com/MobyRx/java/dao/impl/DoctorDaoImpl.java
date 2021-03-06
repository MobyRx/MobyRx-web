package com.MobyRx.java.dao.impl;

import com.MobyRx.java.dao.DoctorDao;
import com.MobyRx.java.entity.common.ProfileEntity;
import com.MobyRx.java.entity.common.UserEntity;
import com.MobyRx.java.entity.doctor.DoctorProfileEntity;

import org.hibernate.Criteria;
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

	public List<DoctorProfileEntity> searchDoctor(Map<String, String> param) {


		Criteria criteria = getCurrentSession().createCriteria(UserEntity.class);
		if (null != param) {
			for (String filedName : param.keySet()) {
				criteria.add(Restrictions.eq(filedName, param.get(filedName)));
			}
		}
		/*criteria.add(Restrictions.disjunction()
				.add(Restrictions.eq("mobile", param.get("mobile"))));*/
		List<UserEntity> userEntity =(List<UserEntity>)criteria.list();

		Criteria docCriteria = getCurrentSession().createCriteria(ProfileEntity.class);
		docCriteria.add(Restrictions.disjunction()
				.add(Restrictions.eq("user.id", userEntity.get(0).getId())));
		List<DoctorProfileEntity> doctorProfileEntity=(List<DoctorProfileEntity>)docCriteria.list();
		
		return doctorProfileEntity;

	}


}
