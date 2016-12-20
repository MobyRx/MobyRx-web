package com.MobyRx.java.dao;

import com.MobyRx.java.entity.common.AccountEntity;
import com.MobyRx.java.entity.type.AccountType;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 12/19/16
 * Time: 9:58 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AccountDao extends BaseDao{
    
    
    List<AccountEntity> getAccount(AccountType accountType, Map<String,String> filterMap);

    List<AccountEntity> getAccount(Map<String, String> filterMap);

    AccountEntity getAccount(Long accountId);

}
