package com.MobyRx.java.bl;

import java.util.List;

import com.MobyRx.java.entity.PrescriptionEntity;
import com.MobyRx.java.service.wso.PatientProfileWSO;
import com.MobyRx.java.service.wso.PrescriptionWSO;
import com.MobyRx.java.service.wso.StatusWSO;

public interface PrescriptionBL {
	
	void save(PrescriptionWSO prescriptionWSO, StatusWSO statusWSO) throws Exception;

	List<PrescriptionEntity> getPrescriptionByPatientId(long parseLong, StatusWSO statusWSO)throws Exception;
}
