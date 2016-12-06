package com.MobyRx.java.bl;


import com.MobyRx.java.entity.common.AccountEntity;
import com.MobyRx.java.service.wso.ClinicWSO;
import com.MobyRx.java.service.wso.StatusWSO;

public interface ClinicBL {

	public void save(ClinicWSO clinicWSO,StatusWSO statusWSO) throws Exception;
	public void update(ClinicWSO clinicWSO,StatusWSO statusWSO) throws Exception;
	public AccountEntity get(long id,StatusWSO statusWSO) throws Exception;
	public void delete(long id,StatusWSO statusWSO) throws Exception;

}
