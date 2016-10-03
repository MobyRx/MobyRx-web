package com.MobyRx.java.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.MobyRx.java.dao.DrugDao;
import com.MobyRx.java.entity.DrugsEntity;

@Repository("drugDao")
public class DrugDaoImpl extends BaseDaoImpl implements DrugDao {
	
	 public List<DrugsEntity> searchDrugs(Map<String, Object> fieldParam, String query) {
	        Criteria criteria = getCurrentSession().createCriteria(DrugsEntity.class);
	        if (null != fieldParam) {
	            for (String filedName : fieldParam.keySet()) {
	                criteria.add(Restrictions.eq(filedName, fieldParam.get(filedName)));
	            }
	        }
	        criteria.add(Restrictions.disjunction()
	                .add(Restrictions.ilike("name", query, MatchMode.START)));
	        return criteria.list();
	    }


}
