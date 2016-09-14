package com.MobyRx.java.dao.impl;

import com.MobyRx.java.dao.DoctorDao;
import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.entity.DrugsEntity;
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


    public List<DrugsEntity> searchDrugs(Map<String, Object> fieldParam, String query) {
        Criteria criteria = getCurrentSession().createCriteria(DrugsEntity.class);
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
