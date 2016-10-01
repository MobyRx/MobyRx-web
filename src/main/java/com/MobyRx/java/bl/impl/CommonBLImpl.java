package com.MobyRx.java.bl.impl;

import com.MobyRx.java.bl.CommonBL;
import com.MobyRx.java.dao.CommonDao;
import com.MobyRx.java.dao.DoctorDao;
import com.MobyRx.java.dao.impl.ClinicDaoImpl;
import com.MobyRx.java.entity.AddressEntity;
import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.entity.master.ServiceEntity;
import com.MobyRx.java.service.wso.ClinicWSO;
import com.MobyRx.java.service.wso.ServiceWSO;
import com.MobyRx.java.service.wso.StatusWSO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
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

    @Override
    public void executeSQLQueryUpdate(String sqlQuery) {
        commonDao.executeSQLQueryUpdate(sqlQuery);
    }
    
    @Override
    public List<DrugsEntity> searchDrugs(String query) {
        Map<String,Object> param = new HashMap<String, Object>();
        List<DrugsEntity> drugs = doctorDao.searchDrugs(param, query);
        return drugs;
    }


}
