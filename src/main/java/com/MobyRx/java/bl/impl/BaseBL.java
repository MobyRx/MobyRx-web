package com.MobyRx.java.bl.impl;

import java.util.List;

import com.MobyRx.java.entity.BaseEntity;
import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.service.wso.ClinicWSO;
import com.MobyRx.java.service.wso.StatusWSO;
import org.springframework.http.HttpStatus;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/5/16
 * Time: 6:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaseBL{

    protected void saveErrorMessage(StatusWSO status, int code) {
        status.setCode(code);
    }

    protected void saveSuccessMessage(StatusWSO status, String message) {
        status.setCode(HttpStatus.OK.value());
        status.setMessage(message);
    }

    protected void saveSuccessMessage(StatusWSO status) {
        status.setCode(HttpStatus.OK.value());
    }

	public void executeSQLQueryUpdate(String sqlQuery) {
		// TODO Auto-generated method stub
		
	}

	public List getMasterData(String className) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DrugsEntity> searchDrugs(String query) {
		// TODO Auto-generated method stub
		return null;
	}


}
