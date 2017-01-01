package com.MobyRx.java.dao.impl;

import com.MobyRx.java.dao.PatientDao;
import com.MobyRx.java.entity.common.ProfileEntity;
import com.MobyRx.java.entity.common.UserEntity;
import com.MobyRx.java.entity.patient.AppointmentEntity;
import com.MobyRx.java.entity.patient.PatientProfileEntity;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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
    
    
    public Integer getNextToken(Long doctorId,  Long clinicId){
        Criteria criteria = getCurrentSession().createCriteria(AppointmentEntity.class)
                .add(Restrictions.eq("doctor.id", doctorId))
                .add(Restrictions.eq("clinic.id", clinicId))
                .addOrder(Order.desc("token"))
                .setMaxResults(1)
                .setProjection(Projections.property("token"));
        Integer lastToken = (Integer)criteria.uniqueResult();
        return null!=lastToken?lastToken+1:1;
    }

    @Override
    public AppointmentEntity getAppointment(Long appointId) {
        Criteria criteria = getCurrentSession().createCriteria(AppointmentEntity.class)
                .add(Restrictions.eq("id", appointId));
        return (AppointmentEntity)criteria.uniqueResult();
    }

    @Override
    public List<AppointmentEntity> getAppointments(Map<String,String> filterMap) {
        Criteria criteria = getCurrentSession().createCriteria(AppointmentEntity.class);
        for(String key : filterMap.keySet()){
            if(key.contains("id"))
                criteria.add(Restrictions.eq(key, Long.parseLong(filterMap.get(key))));
            else
                criteria.add(Restrictions.eq(key, filterMap.get(key)));
        }
        return criteria.list();
    }

}
