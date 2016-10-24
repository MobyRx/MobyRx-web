package com.MobyRx.java.bl.impl;

import java.util.HashSet;
import java.util.List;
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
		if(doctorProfileWSO.getClinic().size()>0)
		{
			doctorProfileEntity.setClinic(null);
			Set<ClinicEntity> clinicEntityList = new HashSet<ClinicEntity>();
			for(ClinicWSO clinicWSO : doctorProfileWSO.getClinic())
			{
				logger.info("clinicWSO.getId()="+clinicWSO.getId());
				ClinicEntity clinicEntity = doctorDao.get(ClinicEntity.class, clinicWSO.getId());
				clinicEntityList.add(clinicEntity);
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
		// TODO Auto-generated method stub

	}

	public List<DoctorProfileWSO> searchDoctor(String query) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



}
