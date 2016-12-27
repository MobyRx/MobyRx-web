package com.MobyRx.java.bl;


import com.MobyRx.java.entity.common.AccountEntity;
import com.MobyRx.java.service.wso.ClinicWSO;
import com.MobyRx.java.service.wso.DoctorWSO;
import com.MobyRx.java.service.wso.StatusWSO;

import java.util.List;
import java.util.Map;

public interface AccountBL {

    public void save(ClinicWSO clinicWSO, StatusWSO statusWSO) throws Exception;

    public void update(ClinicWSO clinicWSO, StatusWSO statusWSO) throws Exception;

    public AccountEntity get(long id, StatusWSO statusWSO) throws Exception;

    public void delete(long id, StatusWSO statusWSO) throws Exception;

    List<DoctorWSO> getAccountDoctor(Long accountId, Map<String,String> filterParam);

}
