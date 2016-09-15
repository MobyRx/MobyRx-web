package com.MobyRx.java.bl;


import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.service.wso.ClinicWSO;
import com.MobyRx.java.service.wso.StatusWSO;

import java.util.List;
import java.util.Map;
public interface ClinicBL {
	
	public void save(ClinicWSO clinicWSO) throws Exception;

}
