package com.MobyRx.java.bl.impl;

import java.io.OutputStreamWriter;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.MobyRx.java.bl.UserBL;
import com.MobyRx.java.dao.UserDao;
import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.entity.OTPEntity;
import com.MobyRx.java.entity.UserEntity;
import com.MobyRx.java.bl.impl.CommonBLImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Repository("userBL")
@Transactional
public class UserBLImpl extends CommonBLImpl implements UserBL {
	
	@Autowired
    private UserDao userDao;
	
	private Logger logger = LoggerFactory.getLogger(UserBLImpl.class);
	
	public void generateMobileOTP(String userId) throws Exception
	{
		
		logger.info("generateMobileOTP");
		UserEntity userEntity = userDao.get(UserEntity.class, Long.parseLong(userId));

		if(userEntity!=null)
		{
		Random rand = new Random();
		String otp = String.format("%04d", rand.nextInt(10000));
		OTPEntity otpEntity = new OTPEntity();
		otpEntity.setOtp(otp);
		otpEntity.setSendFrom("MobyRx");
		otpEntity.setSentTo(userEntity.getMobile());
		otpEntity.setUser(userEntity);
		
		userDao.save(otpEntity);
		logger.info("sendSms");
		sendSms(userEntity.getMobile(),otp);
		}
		
		
	}
	
	public boolean verifyMobileOTP(String userId,String otp) throws Exception
	{
		boolean result=false;
		List<OTPEntity> otpEntity = userDao.get(OTPEntity.class, "user",Long.parseLong(userId));
		for(int i=0;i<otpEntity.size();i++)
		{
			if(otpEntity.get(i).getOtp().equals(otp))
			{
				result=true;
				userDao.delete(OTPEntity.class, otpEntity.get(i).getId());
			}
		
		}
		return result;
	}
	
	public static String sendSms(String phonenumber,String otp) throws Exception{
		String sResult = null;

		String data="user=" + URLEncoder.encode("udaychandu", "UTF-8");
		data +="&password=" + URLEncoder.encode("Nagaraju1987", "UTF-8");
		data +="&message=" + URLEncoder.encode(otp, "UTF-8");
		data +="&sender=" + URLEncoder.encode("MobyRx", "UTF-8");
		data +="&mobile=" + URLEncoder.encode(phonenumber, "UTF-8");
		data +="&type=" + URLEncoder.encode("3", "UTF-8");
		URL url = new URL("http://login.bulksmsgateway.in/sendmessage.php?"+data);
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(data);
		wr.flush();
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		String sResult1="";
		while ((line = rd.readLine()) != null) 
		{
		sResult1=sResult1+line+" ";
		}
		wr.close();
		rd.close();
		return sResult1;
		
	}
}
