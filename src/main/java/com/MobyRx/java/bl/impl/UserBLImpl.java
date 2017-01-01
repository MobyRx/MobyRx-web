package com.MobyRx.java.bl.impl;

import java.io.OutputStreamWriter;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.MobyRx.java.entity.common.ProfileEntity;
import com.MobyRx.java.entity.common.UserEntity;
import com.MobyRx.java.entity.doctor.DoctorProfileEntity;
import com.MobyRx.java.entity.patient.OTPEntity;
import com.MobyRx.java.entity.patient.PatientProfileEntity;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.MobyRx.java.bl.UserBL;
import com.MobyRx.java.dao.UserDao;
import com.MobyRx.java.entity.master.RoleEntity;
import com.MobyRx.java.util.ValidatorUtil;
import com.MobyRx.java.service.wso.RoleWSO;
import com.MobyRx.java.service.wso.StatusWSO;
import com.MobyRx.java.service.wso.UserWSO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@Repository("userBL")
@Transactional
public class UserBLImpl extends CommonBLImpl implements UserBL {

    private Logger logger = LoggerFactory.getLogger(UserBLImpl.class);

	@Autowired
    private UserDao userDao;

	public void save(UserWSO userWSO,StatusWSO statusWSO) throws Exception{
        ValidatorUtil.validate(userWSO,statusWSO);
		if(statusWSO.hasError()){
            saveErrorMessage(statusWSO, HttpStatus.SC_BAD_REQUEST);
            return;
		}
        UserEntity user = new UserEntity();
        user.setEmail(userWSO.getEmail());
        user.setMobile(userWSO.getMobile());
        user.setPassword(userWSO.getPassword());
        RoleEntity role = userDao.getRole(userWSO.getRoles().iterator().next());
        user.addRole(role);
        UserEntity userEntity = userDao.getUserByUsername(user.getMobile());
        if (null == userEntity) {
            userDao.save(user);
            ProfileEntity profile;
            if(role.getName().equals(RoleEntity.ROLE_PATIENT)){
                profile = new PatientProfileEntity();
            }else if(role.getName().equals(RoleEntity.ROLE_DOCTOR)){
                profile = new DoctorProfileEntity();
            } else{
                profile = new ProfileEntity();
            }
            profile.setUser(user);
            profile.setName(userWSO.getName());
            userDao.save(profile);
            statusWSO.setCode(HttpStatus.SC_OK);
        } else {
            statusWSO.setCode(HttpStatus.SC_CONFLICT);
            statusWSO.setMessage("Mobile number :- " + user.getMobile() + " already exist");
        }
	}
	
	public void update(UserWSO userWSO, StatusWSO statusWSO)  throws Exception
	{
		ValidatorUtil validatorUtil = new ValidatorUtil();
		validatorUtil.validate(userWSO,statusWSO);
		if(statusWSO.getCode()==400)
		{
			return;
		}
		UserEntity UserEntity = userDao.get(UserEntity.class,userWSO.getId());
		if(!userWSO.getCreatedAt().toString().isEmpty())
		UserEntity.setCreatedAt(userWSO.getCreatedAt());
		if(!userWSO.getEmail().toString().isEmpty())
		UserEntity.setEmail(userWSO.getEmail());
		if(!userWSO.getMobile().toString().isEmpty())
		UserEntity.setMobile(userWSO.getMobile());
		
		UserEntity.setEmailVerified(userWSO.isEmailVerified());
		
		UserEntity.setMobileVerified(userWSO.isMobileVerified());
		
		if(!userWSO.getPassword().toString().isEmpty())
		UserEntity.setPassword(userWSO.getPassword());
		if(!userWSO.getUpdatedAt().toString().isEmpty())
		UserEntity.setUpdatedAt(userWSO.getUpdatedAt());
		//if(!userWSO.getUsername().toString().isEmpty())
		//UserEntity.setUsername(userWSO.getUsername());
		
		userDao.update(UserEntity);
		
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");
	}
	
	public void delete(Long id, StatusWSO statusWSO)  throws Exception
	{
		UserEntity UserEntity = userDao.get(UserEntity.class,id);
		
		userDao.delete(UserEntity);
		
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");

	}
	
	public void generateMobileOTP(String userId,StatusWSO statusWSO) throws Exception
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
		
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");
		
		
	}
	public boolean verifyMobileOTP(String userId,String otp,StatusWSO statusWSO) throws Exception
	{
		boolean result=false;
		List<OTPEntity> otpEntity = userDao.get(OTPEntity.class, "user",Long.parseLong(userId));
		for(int i=0;i<otpEntity.size();i++)
		{
			if(otpEntity.get(i).getOtp().equals(otp))
			{
				result=true;
				userDao.delete(OTPEntity.class, otpEntity.get(i).getId());
				UserEntity userEntity = userDao.get(UserEntity.class, Long.parseLong(userId));
				userEntity.setMobileVerified(true);
				userDao.update(userEntity);
			}
		
		}
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");
		return result;
	}
	
	public static String sendSms(String phonenumber,String otp) throws Exception{
		String sResult = "";
		OutputStreamWriter wr=null;
		BufferedReader rd=null;
		try
		{
			String data="user=" + URLEncoder.encode("udaychandu", "UTF-8");
			data +="&password=" + URLEncoder.encode("Nagaraju1987", "UTF-8");
			data +="&message=" + URLEncoder.encode("MobyRx OTP:"+otp, "UTF-8");
			data +="&sender=" + URLEncoder.encode("MobyRx", "UTF-8");
			data +="&mobile=" + URLEncoder.encode(phonenumber, "UTF-8");
			data +="&type=" + URLEncoder.encode("3", "UTF-8");
			URL url = new URL("http://login.bulksmsgateway.in/sendmessage.php?"+data);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
			wr.flush();
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) 
			{
				sResult=sResult+line+" ";
			}
		}
		finally
		{
			wr.close();
			rd.close();
		}
		return sResult;

	}

	public UserEntity searchUser(String query) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		UserEntity user = userDao.searchUser(param, query);
		return user;
	}
	
	
}
