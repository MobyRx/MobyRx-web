package com.MobyRx.java.dao.impl;

import com.MobyRx.java.dao.PatientDao;
import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.entity.DoctorProfileEntity;
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

}
