package com.MobyRx.java.dao.impl;

import com.MobyRx.java.dao.AccountDao;
import com.MobyRx.java.entity.common.AccountEntity;
import com.MobyRx.java.entity.type.AccountType;
import org.hibernate.Criteria;
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
public class AccountDaoImpl extends BaseDaoImpl implements AccountDao{

    @Override
    public List<AccountEntity> getAccount(AccountType accountType, Map<String, String> filterMap) {
        Criteria criteria = getCurrentSession().createCriteria(AccountEntity.class)
                .add(Restrictions.eq("accountType", accountType));
        for(String key : filterMap.keySet())
            criteria.add(Restrictions.eq(key, filterMap.get(key)));
        return criteria.list();
    }
}
