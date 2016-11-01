package com.MobyRx.java.bl;

import java.util.List;
import java.util.Map;

import com.MobyRx.java.entity.DoctorProfileEntity;
import com.MobyRx.java.service.wso.DoctorProfileWSO;
import com.MobyRx.java.service.wso.StatusWSO;

public interface DoctorBL {

    public void save(DoctorProfileWSO doctorProfileWSO, StatusWSO statusWSO) throws Exception;

    public void update(DoctorProfileWSO doctorProfileWSO, StatusWSO statusWSO) throws Exception;

    public List<DoctorProfileEntity> searchDoctor(Map<String,String> filterParam) throws Exception;
    
    DoctorProfileEntity get(Long id) throws Exception;

    void deleteDoctor(Long id);



}
