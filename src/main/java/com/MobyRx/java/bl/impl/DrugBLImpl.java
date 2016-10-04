package com.MobyRx.java.bl.impl;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.MobyRx.java.bl.DrugBL;
import com.MobyRx.java.dao.DrugDao;
import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.service.wso.DrugWSO;
import com.MobyRx.java.service.wso.WSOToEntityConversion;
import com.MobyRx.java.service.wso.StatusWSO;


@Repository("drugBL")
@Transactional
public class DrugBLImpl extends CommonBLImpl implements DrugBL {

	@Autowired
	private DrugDao drugDao;


	private Logger logger = LoggerFactory.getLogger(DrugBLImpl.class);


	public void save(DrugWSO drugWSO, StatusWSO statusWSO) throws Exception{
		DrugsEntity drugsEntity=WSOToEntityConversion.drugWSOToDrugsEntity(drugWSO);
		drugDao.save(drugsEntity);

		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");
	}

	public void delete(Long drugId , StatusWSO statusWSO) throws Exception {

		drugDao.delete(DrugsEntity.class, drugId);

		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");
	}

	@Override
	public List<DrugsEntity> searchDrugs(String query) {
		Map<String,Object> param = new HashMap<String, Object>();
		List<DrugsEntity> drugs = drugDao.searchDrugs(param, query);
		return drugs;
	}

}