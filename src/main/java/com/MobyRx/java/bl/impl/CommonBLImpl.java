package com.MobyRx.java.bl.impl;

import com.MobyRx.java.bl.CommonBL;
import com.MobyRx.java.dao.AccountDao;
import com.MobyRx.java.dao.CommonDao;

import com.MobyRx.java.dao.DoctorDao;
import com.MobyRx.java.entity.common.AccountEntity;
import com.MobyRx.java.exception.NoRecordFoundException;
import com.MobyRx.java.service.converter.DataMapper;
import com.MobyRx.java.service.wso.AccountWSO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/5/16
 * Time: 6:59 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("commonBL")
@Transactional
public class CommonBLImpl extends BaseBL implements CommonBL{

    private Logger logger = LoggerFactory.getLogger(CommonBLImpl.class);

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private AccountDao accountDao;

    @Override
    public void executeSQLQueryUpdate(String sqlQuery) {
        commonDao.executeSQLQueryUpdate(sqlQuery);
    }

    @Override
    public List getMasterData(String className) {
        return commonDao.getMasterData(className);
    }

    @Override
    public List<AccountWSO> getAccounts(Map<String, String> filterParam) {
        List<AccountEntity> accountList = accountDao.getAccount(filterParam);
        return DataMapper.transform(accountList);
    }

    @Override
    public AccountWSO getAccount(Long accountId) {
        AccountEntity account =  accountDao.getAccount(accountId);
        if(null==account)
            throw new NoRecordFoundException("No account found for id="+accountId);
        return DataMapper.transform(account);
    }
}
