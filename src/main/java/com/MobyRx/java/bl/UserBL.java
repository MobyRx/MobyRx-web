package com.MobyRx.java.bl;

public interface UserBL {

	void generateMobileOTP(String userId) throws Exception;

	boolean verifyMobileOTP(String userId, String otp) throws Exception;

}
