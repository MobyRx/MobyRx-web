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


    public List<DoctorProfileEntity> getDoctor(Map<String, Object> param) {
        Criteria criteria = getCurrentSession().createCriteria(DoctorProfileEntity.class);
        for (String columnName : param.keySet()) {
            criteria.add(Restrictions.eq(columnName, param.get(columnName)));
        }
        return criteria.list();
    }


    public List<ClinicEntity> searchClinic(Map<String, Object> fieldParam, String query) {
        Criteria criteria = getCurrentSession().createCriteria(ClinicEntity.class);
        if (null != fieldParam) {
            for (String filedName : fieldParam.keySet()) {
                criteria.add(Restrictions.eq(filedName, fieldParam.get(filedName)));
            }
        }
        criteria.add(Restrictions.disjunction()
                .add(Restrictions.ilike("name", query, MatchMode.START))
                .add(Restrictions.ilike("category.name", query, MatchMode.START)));
        return criteria.list();
    }


	public List<PatientProfileEntity> searchPatient(Map<String, Object> param, String query) {

		Criteria criteria = getCurrentSession().createCriteria(UserEntity.class);
		if (null != param) {
			for (String filedName : param.keySet()) {
				criteria.add(Restrictions.eq(filedName, param.get(filedName)));
			}
		}
		criteria.add(Restrictions.disjunction()
				.add(Restrictions.eq("mobile", query.trim())));
		List<UserEntity> userEntity =(List<UserEntity>)criteria.list();
		//List<PatientProfileEntity> patientProfileEntity=null;
		
		Criteria docCriteria = getCurrentSession().createCriteria(ProfileEntity.class);
		docCriteria.add(Restrictions.disjunction()
				.add(Restrictions.eq("user.id", userEntity.get(0).getId())));
		List<PatientProfileEntity> patientProfileEntity=(List<PatientProfileEntity>)docCriteria.list();
		
	/*	if(userEntity!=null)
		{
			try {
				patientProfileEntity = getSQLQuery("select * from profile where user_id="+userEntity.get(0).getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		return patientProfileEntity;

	}

}
