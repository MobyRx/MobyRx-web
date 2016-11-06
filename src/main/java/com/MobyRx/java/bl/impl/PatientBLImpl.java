package com.MobyRx.java.bl.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.entity.DoctorProfileEntity;
import com.MobyRx.java.entity.PatientProfileEntity;
import com.MobyRx.java.entity.UserEntity;
import com.MobyRx.java.service.wso.AddressWSO;
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

		PatientProfileEntity patientProfileEntity=patientDao.get(PatientProfileEntity.class, patientProfileWSO.getId());
		
		AddressWSO addressWSO = patientProfileWSO.getAddress();
		AddressEntity addressEntity = patientDao.get(AddressEntity.class, addressWSO.getId());
		if(addressEntity!=null)
		{
		addressEntity.setBuildingNumber(addressWSO.getBuildingNumber());
		addressEntity.setCity(addressWSO.getCity());
		addressEntity.setCountry(addressWSO.getCountry());
		addressEntity.setCreatedAt(addressWSO.getCreatedAt());
		addressEntity.setLandmark(addressWSO.getLandmark());
		addressEntity.setLatitude(addressWSO.getLatitude());
		addressEntity.setLongitude(addressWSO.getLongitude());
		addressEntity.setState(addressWSO.getState());
		addressEntity.setStreet(addressWSO.getStreet());
		addressEntity.setUpdatedAt(addressWSO.getUpdatedAt());
		addressEntity.setZipCode(addressWSO.getZipCode());
		patientProfileEntity.setAddress(addressEntity);
		}
		
		else
		{
			AddressEntity newAddressEntity=WSOToEntityConversion.addressWSOToAddressEntity(patientProfileWSO.getAddress());
			newAddressEntity.setId(null);
			patientProfileEntity.setAddress(newAddressEntity);
		}
		patientProfileEntity.setAge(patientProfileWSO.getAge());
		patientProfileEntity.setBloodGroup(WSOToEntityConversion.bloodGroupWSOToBloodGroupEntity(patientProfileWSO.getBloodGroup()));
		patientProfileEntity.setCreatedAt(patientProfileWSO.getCreatedAt());
		patientProfileEntity.setDob(patientProfileWSO.getDob());
		patientProfileEntity.setEmergencyContacts(WSOToEntityConversion.emergencyContactToEmergencyContactEntity(patientProfileWSO.getEmergencyContacts()));
		patientProfileEntity.setGender(WSOToEntityConversion.genderWSOTOGenderEntity(patientProfileWSO.getGender()));

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

	public void delete(Long patientId, StatusWSO statusWSO)  throws Exception
	{
		PatientProfileEntity patientProfileEntity = patientDao.get(PatientProfileEntity.class,patientId);
		
		patientDao.delete(patientProfileEntity);
		
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");

	}
	
	public List<PatientProfileEntity> searchPatient(String query) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		List<PatientProfileEntity> patientProfile = patientDao.searchPatient(param, query);
		return patientProfile;
	}

    public PatientProfileEntity getPatient(Long id) throws Exception {
    	PatientProfileEntity patientProfileEntity = patientDao.get(PatientProfileEntity.class, id);
    	
        return patientProfileEntity;
    }

    public List<PatientProfileEntity> getDependentPatient(Long id) throws Exception {
        return patientDao.get(PatientProfileEntity.class, "parentPatient", id);
    }

}
