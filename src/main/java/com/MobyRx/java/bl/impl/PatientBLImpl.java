package com.MobyRx.java.bl.impl;

import java.util.List;

import com.MobyRx.java.entity.ProfileEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.MobyRx.java.bl.DoctorBL;
import com.MobyRx.java.bl.PatientBL;
import com.MobyRx.java.dao.DoctorDao;
import com.MobyRx.java.dao.PatientDao;
import com.MobyRx.java.entity.AddressEntity;
import com.MobyRx.java.entity.PatientProfileEntity;
import com.MobyRx.java.entity.UserEntity;
import com.MobyRx.java.service.wso.PatientProfileWSO;
import com.MobyRx.java.service.wso.StatusWSO;
import com.MobyRx.java.service.wso.WSOToEntityConversion;

@Repository("patientBL")
@Transactional
public class PatientBLImpl extends CommonBLImpl implements PatientBL{

	@Autowired
	private PatientDao  patientDao;


	private Logger logger = LoggerFactory.getLogger(PatientBLImpl.class);
	
	public void save(PatientProfileWSO patientProfileWSO, StatusWSO statusWSO) throws Exception {

		PatientProfileEntity patientProfileEntity=new PatientProfileEntity();
		AddressEntity addressEntity=WSOToEntityConversion.addressWSOToAddressEntity(patientProfileWSO.getAddress());
		addressEntity.setId(null);
		patientProfileEntity.setAddress(addressEntity);
		patientProfileEntity.setAge(patientProfileWSO.getAge());
		patientProfileEntity.setBloodGroup(WSOToEntityConversion.bloodGroupWSOToBloodGroupEntity(patientProfileWSO.getBloodGroup()));
		patientProfileEntity.setCreatedAt(patientProfileWSO.getCreatedAt());
		patientProfileEntity.setDob(patientProfileWSO.getDob());
		patientProfileEntity.setEmergencyContacts(WSOToEntityConversion.emergencyContactToEmergencyContactEntity(patientProfileWSO.getEmergencyContacts()));
		patientProfileEntity.setGender(WSOToEntityConversion.genderWSOTOGenderEntity(patientProfileWSO.getGender()));
		patientProfileEntity.setId(null);
		patientProfileEntity.setName(patientProfileWSO.getName());
		if( patientProfileWSO.getParentPatient()!=null && patientProfileWSO.getParentPatient().getId()!=null
				&& !patientProfileWSO.getParentPatient().getId().toString().isEmpty())
		{
			Long pateientId=patientProfileWSO.getParentPatient().getId();
			PatientProfileEntity parentPatientProfileEntity= patientDao.get(PatientProfileEntity.class, pateientId);
			patientProfileEntity.setParentPatient(parentPatientProfileEntity);
			
			patientProfileEntity.setRelationship(WSOToEntityConversion.relationshipEntityWSOToRelationshipTypeEntity(patientProfileWSO.getRelationship()));
		}
		else
		{
			patientProfileEntity.setParentPatient(null);
			patientProfileEntity.setRelationship(null);
		}
		
		patientProfileEntity.setUpdatedAt(patientProfileWSO.getUpdatedAt());
		Long userId= patientProfileWSO.getUser().getId();
		UserEntity userEntity = patientDao.get(UserEntity.class, userId);
		patientProfileEntity.setUser(userEntity);
		patientDao.save(patientProfileEntity);
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");

	
		
	}

	public void update(PatientProfileWSO patientProfileWSO, StatusWSO statusWSO) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public List<PatientProfileWSO> searchPatient(String query) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public PatientProfileEntity getPatient(Long id) throws Exception {
        return patientDao.get(PatientProfileEntity.class, id);
    }

    @Override
    public List<PatientProfileEntity> getDependentPatient(Long id) throws Exception {
        return patientDao.get(PatientProfileEntity.class, "parentPatient", id);
    }

}
