package com.MobyRx.java.bl.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.MobyRx.java.bl.DoctorBL;
import com.MobyRx.java.bl.DrugBL;
import com.MobyRx.java.dao.DoctorDao;
import com.MobyRx.java.dao.DrugDao;
import com.MobyRx.java.entity.AddressEntity;
import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.entity.DoctorProfileEntity;
import com.MobyRx.java.entity.UserEntity;
import com.MobyRx.java.entity.master.SpecializationEntity;
import com.MobyRx.java.service.wso.ClinicWSO;
import com.MobyRx.java.service.wso.DoctorProfileWSO;
import com.MobyRx.java.service.wso.DrugWSO;
import com.MobyRx.java.service.wso.SpecializationWSO;
import com.MobyRx.java.service.wso.StatusWSO;
import com.MobyRx.java.service.wso.WSOToEntityConversion;

@Repository("doctorBL")
@Transactional
public class DoctorBLImpl extends CommonBLImpl implements DoctorBL{

	@Autowired
	private DoctorDao doctorDao;


	private Logger logger = LoggerFactory.getLogger(DoctorBLImpl.class);

	public void save(DoctorProfileWSO doctorProfileWSO, StatusWSO statusWSO) throws Exception {
		DoctorProfileEntity doctorProfileEntity = new DoctorProfileEntity();
		doctorProfileEntity.setAchievements(doctorProfileWSO.getAchievements());
		AddressEntity addressEntity = WSOToEntityConversion.addressWSOToAddressEntity(doctorProfileWSO.getAddress());
		doctorProfileEntity.setAddress(addressEntity);
		doctorProfileEntity.setCertificateNumber(doctorProfileWSO.getCertificateNumber());
		doctorProfileEntity.setCertification(doctorProfileWSO.getCertification());
		doctorProfileEntity.setClinic(null);
		if(doctorProfileWSO.getClinic().size()>0)
		{
			Set<ClinicEntity> clinicEntityList = doctorProfileEntity.getClinic();
			for(ClinicWSO clinicWSO : doctorProfileWSO.getClinic())
			{
				logger.info("clinicWSO.getId()="+clinicWSO.getId());
				ClinicEntity clinicEntity = doctorDao.get(ClinicEntity.class, clinicWSO.getId());
				if(clinicEntity==null)
				{
					ClinicEntity newClinicEntity = WSOToEntityConversion.ClinicWSOToClinicEntity(clinicWSO);
					clinicEntityList.add(newClinicEntity);
					
				}
				else
				{
				clinicEntityList.add(clinicEntity);
				}
			}
			doctorProfileEntity.setClinic(clinicEntityList);
		}
		doctorProfileEntity.setCreatedAt(doctorProfileWSO.getCreatedAt());
		doctorProfileEntity.setEmergencyContacts(WSOToEntityConversion.emergencyContactToEmergencyContactEntity(doctorProfileWSO.getEmergencyContacts()));
		doctorProfileEntity.setGender(WSOToEntityConversion.genderWSOTOGenderEntity(doctorProfileWSO.getGender()));
		doctorProfileEntity.setMedRegNumber(doctorProfileWSO.getMedRegNumber());
		doctorProfileEntity.setName(doctorProfileWSO.getName());
		doctorProfileEntity.setPracticeStartAt(doctorProfileWSO.getPracticeStartAt());
		doctorProfileEntity.setQualification(doctorProfileWSO.getQualification());
		Set<SpecializationEntity> specializationEntityList = new HashSet<SpecializationEntity>();
		if(doctorProfileWSO.getSpecializations().size()>0)
		{
			for(SpecializationWSO specializationWSO : doctorProfileWSO.getSpecializations())
			{
				SpecializationEntity SpecializationEntity = doctorDao.get(SpecializationEntity.class, specializationWSO.getId());
				specializationEntityList.add(SpecializationEntity);
			}
			doctorProfileEntity.setSpecializations(specializationEntityList);
		}
		else
		{
			doctorProfileEntity.setSpecializations(null);
		}
		doctorProfileEntity.setUpdatedAt(doctorProfileWSO.getUpdatedAt());
		Long userId= doctorProfileWSO.getUser().getId();
		UserEntity userEntity = (UserEntity)doctorDao.get(UserEntity.class, userId);
		doctorProfileEntity.setUser(userEntity);
		doctorProfileEntity.setVerified(doctorProfileWSO.isVerified());
		doctorDao.save(doctorProfileEntity);
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");

	}

	public void update(DoctorProfileWSO doctorProfileWSO, StatusWSO statusWSO) throws Exception {
		DoctorProfileEntity doctorProfileEntity = doctorDao.get(DoctorProfileEntity.class, doctorProfileWSO.getId());
		doctorProfileEntity.setAchievements(doctorProfileWSO.getAchievements());
		
		AddressEntity addressEntity = doctorProfileEntity.getAddress();
		if(addressEntity!=null)
		{
		addressEntity.setBuildingNumber(doctorProfileWSO.getAddress().getBuildingNumber());
		addressEntity.setCity(doctorProfileWSO.getAddress().getCity());
		addressEntity.setCountry(doctorProfileWSO.getAddress().getCountry());
		addressEntity.setCreatedAt(doctorProfileWSO.getCreatedAt());
		addressEntity.setLandmark(doctorProfileWSO.getAddress().getLandmark());
		addressEntity.setLatitude(doctorProfileWSO.getAddress().getLatitude());
		addressEntity.setLongitude(doctorProfileWSO.getAddress().getLongitude());
		addressEntity.setState(doctorProfileWSO.getAddress().getState());
		addressEntity.setStreet(doctorProfileWSO.getAddress().getStreet());
		addressEntity.setUpdatedAt(doctorProfileWSO.getUpdatedAt());
		addressEntity.setZipCode(doctorProfileWSO.getAddress().getZipCode());
		doctorProfileEntity.setAddress(addressEntity);
		}
		
		else
		{
			AddressEntity newAddressEntity=WSOToEntityConversion.addressWSOToAddressEntity(doctorProfileWSO.getAddress());
			newAddressEntity.setId(null);
			doctorProfileEntity.setAddress(newAddressEntity);
		}
		doctorProfileEntity.setCertificateNumber(doctorProfileWSO.getCertificateNumber());
		doctorProfileEntity.setCertification(doctorProfileWSO.getCertification());
		if(doctorProfileWSO.getClinic().size()>0)
		{
			Set<ClinicEntity> clinicEntityList = doctorProfileEntity.getClinic();
			for(ClinicWSO clinicWSO : doctorProfileWSO.getClinic())
			{
				logger.info("clinicWSO.getId()="+clinicWSO.getId());
				ClinicEntity clinicEntity = doctorDao.get(ClinicEntity.class, clinicWSO.getId());
				if(clinicEntity==null)
				{
					ClinicEntity newClinicEntity = WSOToEntityConversion.ClinicWSOToClinicEntity(clinicWSO);
					clinicEntityList.add(newClinicEntity);
					
				}
				else
				{
				clinicEntityList.add(clinicEntity);
				}
			}
			doctorProfileEntity.setClinic(clinicEntityList);
		}
		doctorProfileEntity.setCreatedAt(doctorProfileWSO.getCreatedAt());
		doctorProfileEntity.setEmergencyContacts(WSOToEntityConversion.emergencyContactToEmergencyContactEntity(doctorProfileWSO.getEmergencyContacts()));
		doctorProfileEntity.setGender(WSOToEntityConversion.genderWSOTOGenderEntity(doctorProfileWSO.getGender())); doctorProfileEntity.getGender();
		doctorProfileEntity.setMedRegNumber(doctorProfileWSO.getMedRegNumber());
		doctorProfileEntity.setName(doctorProfileWSO.getName());
		doctorProfileEntity.setPracticeStartAt(doctorProfileWSO.getPracticeStartAt());
		doctorProfileEntity.setQualification(doctorProfileWSO.getQualification());
		Set<SpecializationEntity> specializationEntityList = doctorProfileEntity.getSpecializations();
		if(doctorProfileWSO.getSpecializations().size()>0)
		{
			for(SpecializationWSO specializationWSO : doctorProfileWSO.getSpecializations())
			{
				SpecializationEntity SpecializationEntity = doctorDao.get(SpecializationEntity.class, specializationWSO.getId());
				specializationEntityList.add(SpecializationEntity);
			}
			doctorProfileEntity.setSpecializations(specializationEntityList);
		}
		else
		{
			doctorProfileEntity.setSpecializations(null);
		}
		doctorProfileEntity.setUpdatedAt(doctorProfileWSO.getUpdatedAt());
		Long userId= doctorProfileWSO.getUser().getId();
		UserEntity userEntity = (UserEntity)doctorDao.get(UserEntity.class, userId);
		doctorProfileEntity.setUser(userEntity);
		doctorProfileEntity.setVerified(doctorProfileWSO.isVerified());
		doctorDao.save(doctorProfileEntity);
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");

	}

	public void delete(Long doctorId, StatusWSO statusWSO)  throws Exception
	{
		UserEntity UserEntity = doctorDao.get(UserEntity.class,doctorId);
		
		doctorDao.delete(UserEntity);
		
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");

	}
	
	public List<DoctorProfileEntity> searchDoctor(String query) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		List<DoctorProfileEntity> doctorProfile = doctorDao.searchDoctor(param, query);
		return doctorProfile;
	}



}
