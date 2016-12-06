package com.MobyRx.java.bl;

import java.util.List;
import java.util.Map;

import com.MobyRx.java.entity.patient.PatientProfileEntity;
import com.MobyRx.java.service.wso.PatientProfileWSO;
import com.MobyRx.java.service.wso.StatusWSO;

public interface PatientBL {
    
     void save(PatientProfileWSO patientProfileWSO, StatusWSO statusWSO) throws Exception;

     void update(PatientProfileWSO patientProfileWSO, StatusWSO statusWSO) throws Exception;

     List<PatientProfileEntity> searchPatient(Map<String, String> queryParam) throws Exception;

    PatientProfileEntity getPatient(Long id) throws Exception;

    List<PatientProfileEntity> getDependentPatient(Long id) throws Exception;

}
