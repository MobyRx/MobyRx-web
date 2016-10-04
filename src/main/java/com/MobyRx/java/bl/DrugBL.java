package com.MobyRx.java.bl;

import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.service.wso.DrugWSO;
import com.MobyRx.java.service.wso.StatusWSO;
import java.util.List;
public interface DrugBL {

	public void save(DrugWSO drugWSO, StatusWSO statusWSO) throws Exception;
	public void delete(Long drugId , StatusWSO statusWSO)throws Exception;
	public List<DrugsEntity> searchDrugs(String query) throws Exception;
}

