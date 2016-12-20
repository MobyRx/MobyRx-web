package com.MobyRx.java.dao.impl;

import com.MobyRx.java.dao.AccountDao;
import com.MobyRx.java.entity.common.AccountEntity;
import com.MobyRx.java.entity.type.AccountType;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 12/19/16
 * Time: 10:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("accountDao")
public class AccountDaoImpl extends BaseDaoImpl implements AccountDao {

    @Override
    public List<AccountEntity> getAccount(AccountType accountType, Map<String, String> filterMap) {
        filterMap.put("accountType", accountType.name());
        return getAccount(filterMap);
    }

    @Override
    public List<AccountEntity> getAccount(Map<String, String> filterMap) {
        StringBuilder hql = new StringBuilder("select acc from AccountEntity acc");
        String token = " where ";
        for (String key : filterMap.keySet()) {
            String value = filterMap.get(key);
            hql.append(token);
            hql.append(" acc.");
            hql.append(key);
            if(key.equalsIgnoreCase("name")){
                hql.append(" like '%");
                hql.append(value);
                hql.append("%'");
            }else{
                value = value.replaceAll("\\,","','");
                hql.append(" in ('");
                hql.append(value);
                hql.append("')");
            }
            token = " and ";
        }
        Query query = getCurrentSession().createQuery(hql.toString());
        return query.list();
    }

    @Override
    public AccountEntity getAccount(Long accountId) {
        Criteria criteria = getCurrentSession().createCriteria(AccountEntity.class)
                .add(Restrictions.eq("id", accountId));
        return (AccountEntity)criteria.uniqueResult();
    }
}
