package com.MobyRx.java.bl;

import java.util.List;

import com.MobyRx.java.entity.DoctorProfileEntity;
import com.MobyRx.java.service.wso.DoctorProfileWSO;
import com.MobyRx.java.service.wso.PatientProfileWSO;
import com.MobyRx.java.service.wso.StatusWSO;

public interface DoctorBL {
	public void save(DoctorProfileWSO doctorProfileWSO, StatusWSO statusWSO) throws Exception;
	public void update(DoctorProfileWSO doctorProfileWSO, StatusWSO statusWSO) throws Exception;
	public List<DoctorProfileEntity> searchDoctor(String query) throws Exception;
	public void delete(Long doctorId, StatusWSO statusWSO) throws Exception;

}
