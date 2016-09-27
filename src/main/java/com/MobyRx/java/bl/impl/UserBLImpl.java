package com.MobyRx.java.bl.impl;

import java.io.OutputStreamWriter;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.MobyRx.java.bl.UserBL;
import com.MobyRx.java.dao.UserDao;
import com.MobyRx.java.entity.AddressEntity;
import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.entity.DoctorProfileEntity;
import com.MobyRx.java.entity.OTPEntity;
import com.MobyRx.java.entity.PatientProfileEntity;
import com.MobyRx.java.entity.UserEntity;
import com.MobyRx.java.entity.master.RoleEntity;
import com.MobyRx.java.service.wso.DataMapper;
import com.MobyRx.java.service.wso.DoctorProfileWSO;
import com.MobyRx.java.service.wso.PatientProfileWSO;
import com.MobyRx.java.service.wso.RoleWSO;
import com.MobyRx.java.service.wso.StatusWSO;
import com.MobyRx.java.service.wso.UserWSO;
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
	public void addUser(UserWSO userWSO,StatusWSO statusWSO) throws Exception
	{

		String userQuery = "select id from user where mobile="+userWSO.getMobile();
		List userQueryResult= userDao.getSQLQuery(userQuery);
		UserEntity userEntity=null;
		if(userQueryResult!=null && userQueryResult.size()>0)
		{
			Long id =Long.parseLong(userQueryResult.get(0).toString());
			userEntity=(UserEntity)userDao.get(UserEntity.class, id);

			if(userEntity!=null)
			{
				Set<RoleEntity> roleEntity  =userEntity.getRoles();

				Set<RoleWSO> roleWSO = userWSO.getRoles();
				Iterator<RoleWSO> iteratorWSO = roleWSO.iterator(); 
				Iterator<RoleEntity> iteratorRoleEntity = roleEntity.iterator(); 

				while (iteratorWSO.hasNext()){
					RoleWSO tmpWSORole=iteratorWSO.next();
					while (iteratorRoleEntity.hasNext()){
						RoleEntity tmpRoleEntity=iteratorRoleEntity.next();
						if(tmpRoleEntity.getName().equals(tmpWSORole.getName()))
						{
							statusWSO.setCode(400);
							statusWSO.setMessage("User With "+tmpWSORole.getName() + " role already present for mobile number "+userWSO.getMobile());
							return;  
						}

					}
					String roleQuery="select id from role where name='"+tmpWSORole.getName()+"'";
					List roleQueryResult = userDao.getSQLQuery(roleQuery);
					if(roleQueryResult==null || roleQueryResult.size()<=0)
					{

						RoleEntity tmpRoleEntity = new RoleEntity();
						tmpRoleEntity.setDescription(tmpWSORole.getDescription());
						tmpRoleEntity.setName(tmpWSORole.getName());
						roleEntity.add(tmpRoleEntity);

					}
					else
					{
						Long roleId=Long.parseLong(roleQueryResult.get(0).toString());
						RoleEntity oldRoleEntity =userDao.get(RoleEntity.class, roleId);
						roleEntity.add(oldRoleEntity) ; 
					}
					userEntity.setRoles(roleEntity); 
					userDao.save(userEntity);  

				}
			}
		}
		else
		{
			UserEntity newUserEntity = new UserEntity();
			newUserEntity.setCreatedAt(userWSO.getCreatedAt());
			newUserEntity.setEmail(userWSO.getEmail());
			newUserEntity.setMobile(userWSO.getMobile());
			newUserEntity.setEmailVerified(userWSO.isEmailVerified());
			newUserEntity.setMobileVerified(userWSO.isMobileVerified());
			newUserEntity.setPassword(userWSO.getPassword());
			newUserEntity.setUpdatedAt(userWSO.getUpdatedAt());
			newUserEntity.setUsername(userWSO.getUsername());


			Set<RoleWSO> roleWSO = userWSO.getRoles();
			Iterator<RoleWSO> iteratorWSO = roleWSO.iterator(); 
			Set<RoleEntity> roleEntity = new HashSet<RoleEntity>();
			while (iteratorWSO.hasNext()){
				RoleWSO tmpWSORole=iteratorWSO.next();


				String roleQuery="select id from role where name='"+tmpWSORole.getName()+"'";
				List roleQueryResult = userDao.getSQLQuery(roleQuery);

				if(roleQueryResult==null || roleQueryResult.size()<=0)
				{
					RoleEntity tmpRoleEntity = new RoleEntity();
					tmpRoleEntity.setDescription(tmpWSORole.getDescription());
					tmpRoleEntity.setName(tmpWSORole.getName());
					roleEntity.add(tmpRoleEntity);
				}
				else
				{
					Long roleId=Long.parseLong(roleQueryResult.get(0).toString());
					RoleEntity oldRoleEntity=userDao.get(RoleEntity.class,roleId);
					logger.info("oldRoleEntity="+oldRoleEntity);
					roleEntity.add(oldRoleEntity) ; 
				}
				newUserEntity.setRoles(roleEntity);
				userDao.save(newUserEntity);  

			}

		}
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");
		return;  
	}
	
	public void modifyUser(UserWSO userWSO, StatusWSO statusWSO)  throws Exception
	{
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
		if(!userWSO.getUsername().toString().isEmpty())
		UserEntity.setUsername(userWSO.getUsername());
		
		userDao.update(UserEntity);
		
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");
		return; 
	}
	
	public void deleteUser(Long id, StatusWSO statusWSO)  throws Exception
	{
		UserEntity UserEntity = userDao.get(UserEntity.class,id);
		
		userDao.delete(UserEntity);
		
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");
		return; 
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
	public void addDoctor(DoctorProfileWSO doctorProfileWSO, StatusWSO statusWSO) throws Exception {
		DoctorProfileEntity doctorProfileEntity = new DoctorProfileEntity();
		doctorProfileEntity.setAchievements(doctorProfileWSO.getAchievements());
		AddressEntity AddressEntity = DataMapper.addressWSOToAddressEntity(doctorProfileWSO.getAddress());
		AddressEntity.setId(null);
		doctorProfileEntity.setAddress(AddressEntity);
		doctorProfileEntity.setCertificateNumber(doctorProfileWSO.getCertificateNumber());
		doctorProfileEntity.setCertification(doctorProfileWSO.getCertification());
		doctorProfileEntity.setClinic(null);
		doctorProfileEntity.setCreatedAt(doctorProfileWSO.getCreatedAt());
		doctorProfileEntity.setEmergencyContacts(DataMapper.emergencyContactToEmergencyContactEntity(doctorProfileWSO.getEmergencyContacts()));
		doctorProfileEntity.setGender(DataMapper.genderWSOTOGenderEntity(doctorProfileWSO.getGender()));

		doctorProfileEntity.setMedRegNumber(doctorProfileWSO.getMedRegNumber());
		doctorProfileEntity.setName(doctorProfileWSO.getName());
		doctorProfileEntity.setPracticeStartAt(doctorProfileWSO.getPracticeStartAt());
		doctorProfileEntity.setQualification(doctorProfileWSO.getQualification());
		doctorProfileEntity.setSpecializations(DataMapper.specializationWSOToSpecializationEntity(doctorProfileWSO.getSpecializations()));
		doctorProfileEntity.setUpdatedAt(doctorProfileWSO.getUpdatedAt());
		
		Long userId= doctorProfileWSO.getUser().getId();
		UserEntity userEntity = userDao.get(UserEntity.class, userId);
		doctorProfileEntity.setUser(userEntity);
		doctorProfileEntity.setVerified(doctorProfileWSO.isVerified());
		
		userDao.save(doctorProfileEntity);
		
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");
		return;

	}
	public void addPatient(PatientProfileWSO patientProfileWSO,StatusWSO statusWSO) throws Exception{
		PatientProfileEntity patientProfileEntity=new PatientProfileEntity();
		AddressEntity addressEntity=DataMapper.addressWSOToAddressEntity(patientProfileWSO.getAddress());
		addressEntity.setId(null);
		patientProfileEntity.setAddress(addressEntity);
		patientProfileEntity.setAge(patientProfileWSO.getAge());
		patientProfileEntity.setBloodGroup(DataMapper.bloodGroupWSOToBloodGroupEntity(patientProfileWSO.getBloodGroup()));
		patientProfileEntity.setCreatedAt(patientProfileWSO.getCreatedAt());
		patientProfileEntity.setDob(patientProfileWSO.getDob());
		patientProfileEntity.setEmergencyContacts(DataMapper.emergencyContactToEmergencyContactEntity(patientProfileWSO.getEmergencyContacts()));
		patientProfileEntity.setGender(DataMapper.genderWSOTOGenderEntity(patientProfileWSO.getGender()));
		patientProfileEntity.setId(null);
		patientProfileEntity.setName(patientProfileWSO.getName());
		if( patientProfileWSO.getParentPatient()!=null && patientProfileWSO.getParentPatient().getId()!=null
				&& !patientProfileWSO.getParentPatient().getId().toString().isEmpty())
		{
			Long pateientId=patientProfileWSO.getParentPatient().getId();
			PatientProfileEntity parentPatientProfileEntity= userDao.get(PatientProfileEntity.class, pateientId);
			patientProfileEntity.setParentPatient(parentPatientProfileEntity);
			
			patientProfileEntity.setRelationship(DataMapper.relationshipEntityWSOToRelationshipTypeEntity(patientProfileWSO.getRelationship()));
		}
		else
		{
			patientProfileEntity.setParentPatient(null);
			patientProfileEntity.setRelationship(null);
		}
		
		patientProfileEntity.setUpdatedAt(patientProfileWSO.getUpdatedAt());
		Long userId= patientProfileWSO.getUser().getId();
		UserEntity userEntity =userEntity= userDao.get(UserEntity.class, userId);
		patientProfileEntity.setUser(userEntity);
		userDao.save(patientProfileEntity);
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");
		return; 
		
		
	}
	
}
