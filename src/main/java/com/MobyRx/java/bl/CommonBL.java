package com.MobyRx.java.bl;

import com.MobyRx.java.service.wso.AccountWSO;
import com.MobyRx.java.service.wso.DoctorProfileWSO;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/5/16
 * Time: 7:03 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CommonBL {

    void executeSQLQueryUpdate(String sqlQuery);
    
    List getMasterData(String className);
    
    List<AccountWSO> getAccounts(Map<String,String> filterParam);

    AccountWSO getAccount(Long accountId);
    



}
