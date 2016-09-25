package com.MobyRx.java.bl;

import com.MobyRx.java.service.wso.DoctorProfileWSO;
import com.MobyRx.java.service.wso.PatientProfileWSO;
import com.MobyRx.java.service.wso.StatusWSO;
import com.MobyRx.java.service.wso.UserWSO;

public interface UserBL {

	void generateMobileOTP(String userId) throws Exception;

	boolean verifyMobileOTP(String userId, String otp) throws Exception;

	void addUser(UserWSO userWSO, StatusWSO statusWSO) throws Exception;

	void modifyUser(UserWSO userWSO, StatusWSO statusWSO)  throws Exception;
	
	void deleteUser(Long id, StatusWSO statusWSO)  throws Exception;

	void addDoctor(DoctorProfileWSO doctorProfileWSO, StatusWSO statusWSO)throws Exception;
	void addPatient(PatientProfileWSO PatientProfileWSO, StatusWSO statusWSO)throws Exception;

}
