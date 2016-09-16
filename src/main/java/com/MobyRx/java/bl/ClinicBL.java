package com.MobyRx.java.bl;


import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.service.wso.ClinicWSO;
import com.MobyRx.java.service.wso.StatusWSO;

import java.util.List;
import java.util.Map;
public interface ClinicBL {
	
	public void save(ClinicWSO clinicWSO) throws Exception;
	public void modify(ClinicWSO clinicWSO) throws Exception;
	public ClinicWSO get(long id) throws Exception;
	public void delete(long id) throws Exception;

}
