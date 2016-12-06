package com.MobyRx.java.bl.impl;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.MobyRx.java.entity.doctor.DrugsEntity;
import com.MobyRx.java.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.MobyRx.java.bl.DrugBL;
import com.MobyRx.java.dao.DrugDao;
import com.MobyRx.java.service.wso.DrugWSO;
import com.MobyRx.java.service.wso.WSOToEntityConversion;
import com.MobyRx.java.service.wso.StatusWSO;


@Repository("drugBL")
@Transactional
public class DrugBLImpl extends BaseBL implements DrugBL {

    private static Logger logger = LoggerFactory.getLogger(DrugBLImpl.class);

	@Autowired
	private DrugDao drugDao;

	public void save(DrugWSO drugWSO, StatusWSO status) throws Exception{
        ValidatorUtil.validate(drugWSO, status);
        if(status.hasError()){
            saveErrorMessage(status, HttpStatus.BAD_REQUEST.value());
        }
		DrugsEntity drugsEntity=WSOToEntityConversion.transform(drugWSO);
		drugDao.save(drugsEntity);
        saveSuccessMessage(status, "Save successfully");
	}

	public void delete(Long drugId , StatusWSO status) throws Exception {
		drugDao.delete(DrugsEntity.class, drugId);
        saveSuccessMessage(status, "Deleted successfully");
	}

	@Override
	public List<DrugsEntity> searchDrugs(String query) {
		Map<String,Object> param = new HashMap<String, Object>();
		return this.drugDao.searchDrugs(param, query);
	}

    public DrugsEntity get(Long id) throws Exception {
        return drugDao.get(DrugsEntity.class, id);
    }

}