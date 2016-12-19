package com.MobyRx.java.dao.impl;

import com.MobyRx.java.dao.AccountDao;
import com.MobyRx.java.entity.common.AccountEntity;
import com.MobyRx.java.entity.type.AccountType;
import org.hibernate.Query;
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
        StringBuilder hql = new StringBuilder("select acc from AccountEntity acc where acc.accountType ='" + accountType.name() + "'");
        for (String key : filterMap.keySet()) {
            String value = filterMap.get(key);
            value = value.replaceAll("\\,","','");
            hql.append(" and acc.");
            hql.append(key);
            hql.append(" in ('");
            hql.append(value);
            hql.append("')");
        }
        Query query = getCurrentSession().createQuery(hql.toString());
        return query.list();
    }
}
