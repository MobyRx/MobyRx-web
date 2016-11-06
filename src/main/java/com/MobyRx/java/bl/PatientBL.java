package com.MobyRx.java.bl;

import java.util.List;

import com.MobyRx.java.entity.DoctorProfileEntity;
import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.entity.PatientProfileEntity;
import com.MobyRx.java.entity.ProfileEntity;
import com.MobyRx.java.service.wso.DrugWSO;
import com.MobyRx.java.service.wso.PatientProfileWSO;
import com.MobyRx.java.service.wso.StatusWSO;

public interface PatientBL {
    
     void save(PatientProfileWSO patientProfileWSO, StatusWSO statusWSO) throws Exception;

     void update(PatientProfileWSO patientProfileWSO, StatusWSO statusWSO) throws Exception;

     List<PatientProfileEntity> searchPatient(String query) throws Exception;

    PatientProfileEntity getPatient(Long id) throws Exception;

    List<PatientProfileEntity> getDependentPatient(Long id) throws Exception;

	void delete(Long id, StatusWSO statusWSO) throws Exception;

	

}
