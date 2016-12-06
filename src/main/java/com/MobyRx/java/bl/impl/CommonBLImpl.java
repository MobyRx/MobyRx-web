package com.MobyRx.java.bl.impl;

import com.MobyRx.java.bl.CommonBL;
import com.MobyRx.java.dao.CommonDao;

import com.MobyRx.java.dao.DoctorDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public void executeSQLQueryUpdate(String sqlQuery) {
        commonDao.executeSQLQueryUpdate(sqlQuery);
    }

    @Override
    public List getMasterData(String className) {
        return commonDao.getMasterData(className);
    }
}
