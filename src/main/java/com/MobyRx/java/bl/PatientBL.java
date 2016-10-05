package com.MobyRx.java.bl;

import java.util.List;

import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.service.wso.DrugWSO;
import com.MobyRx.java.service.wso.PatientProfileWSO;
import com.MobyRx.java.service.wso.StatusWSO;

public interface PatientBL {
	public void save(PatientProfileWSO patientProfileWSO, StatusWSO statusWSO) throws Exception;
	public void update(PatientProfileWSO patientProfileWSO, StatusWSO statusWSO) throws Exception;
	public List<PatientProfileWSO> searchPatient(String query) throws Exception;

}
