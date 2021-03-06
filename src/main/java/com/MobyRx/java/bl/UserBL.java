package com.MobyRx.java.bl;

import com.MobyRx.java.entity.common.UserEntity;
import com.MobyRx.java.service.wso.StatusWSO;
import com.MobyRx.java.service.wso.UserWSO;

public interface UserBL {

	void generateMobileOTP(String userId,StatusWSO statusWSO) throws Exception;

	boolean verifyMobileOTP(String userId, String otp,StatusWSO statusWSO) throws Exception;

	void save(UserWSO userWSO, StatusWSO statusWSO) throws Exception;

	void update(UserWSO userWSO, StatusWSO statusWSO)  throws Exception;
	
	void delete(Long id, StatusWSO statusWSO)  throws Exception;


	UserEntity searchUser(String query) throws Exception;

}
