package com.MobyRx.java.dao.impl;

import com.MobyRx.java.dao.ClinicDao;
import com.MobyRx.java.entity.AddressEntity;
import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.entity.DoctorProfileEntity;
import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.entity.master.ServiceEntity;
import com.MobyRx.java.service.ClinicService;
import com.MobyRx.java.service.wso.ClinicWSO;
import com.MobyRx.java.service.wso.ServiceWSO;
import com.MobyRx.java.service.wso.StatusWSO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/8/16
 * Time: 7:51 PM
 * To change this template use File | Settings | File Templates.
 */
@Transactional
@Repository("clinicDao")
public class ClinicDaoImpl extends BaseDaoImpl implements ClinicDao {
	
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
