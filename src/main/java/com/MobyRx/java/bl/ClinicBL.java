package com.MobyRx.java.bl;


import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.service.wso.ClinicWSO;
import com.MobyRx.java.service.wso.StatusWSO;

import java.util.List;
import java.util.Map;
public interface ClinicBL {
	
	public void save(ClinicWSO clinicWSO,StatusWSO statusWSO) throws Exception;
	public void update(ClinicWSO clinicWSO,StatusWSO statusWSO) throws Exception;
	public ClinicWSO get(long id,StatusWSO statusWSO) throws Exception;
	public void delete(long id,StatusWSO statusWSO) throws Exception;

}
