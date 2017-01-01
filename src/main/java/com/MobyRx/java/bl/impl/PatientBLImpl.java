package com.MobyRx.java.bl.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.MobyRx.java.entity.common.AccountEntity;
import com.MobyRx.java.entity.common.AddressEntity;
import com.MobyRx.java.entity.common.ProfileEntity;
import com.MobyRx.java.entity.common.UserEntity;
import com.MobyRx.java.entity.patient.AppointmentEntity;
import com.MobyRx.java.entity.patient.PatientProfileEntity;
import com.MobyRx.java.entity.type.AppointmentStatus;
import com.MobyRx.java.exception.NoRecordFoundException;
import com.MobyRx.java.service.converter.DataMapper;
import com.MobyRx.java.service.wso.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.MobyRx.java.bl.PatientBL;
import com.MobyRx.java.dao.PatientDao;

@Repository("patientBL")
@Transactional
public class PatientBLImpl extends CommonBLImpl implements PatientBL{

	@Autowired
	private PatientDao  patientDao;


	private Logger logger = LoggerFactory.getLogger(PatientBLImpl.class);
	
	public void save(PatientProfileWSO patientProfileWSO, StatusWSO statusWSO) throws Exception {

		PatientProfileEntity patientProfileEntity=new PatientProfileEntity();
		AddressEntity addressEntity=WSOToEntityConversion.transform(patientProfileWSO.getAddress());
		addressEntity.setId(null);
		patientProfileEntity.setAddress(addressEntity);
		patientProfileEntity.setAge(patientProfileWSO.getAge());
		patientProfileEntity.setBloodGroup(WSOToEntityConversion.transform(patientProfileWSO.getBloodGroup()));
		patientProfileEntity.setDob(patientProfileWSO.getDob());
		patientProfileEntity.setEmergencyContacts(WSOToEntityConversion.transformContacts(patientProfileWSO.getEmergencyContacts()));
		patientProfileEntity.setGender(WSOToEntityConversion.transform(patientProfileWSO.getGender()));
		patientProfileEntity.setId(null);
		patientProfileEntity.setName(patientProfileWSO.getName());
		if( patientProfileWSO.getParentPatient()!=null && patientProfileWSO.getParentPatient().getId()!=null
				&& !patientProfileWSO.getParentPatient().getId().toString().isEmpty())
		{
			Long pateientId=patientProfileWSO.getParentPatient().getId();
			PatientProfileEntity parentPatientProfileEntity= patientDao.get(PatientProfileEntity.class, pateientId);
			patientProfileEntity.setParentPatient(parentPatientProfileEntity);
			
			patientProfileEntity.setRelationship(WSOToEntityConversion.transform(patientProfileWSO.getRelationship()));
		}
		else
		{
			patientProfileEntity.setParentPatient(null);
			patientProfileEntity.setRelationship(null);
		}
		
		Long userId= patientProfileWSO.getUser().getId();
		UserEntity userEntity = patientDao.get(UserEntity.class, userId);
		patientProfileEntity.setUser(userEntity);
		patientDao.save(patientProfileEntity);
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");

	
		
	}

	

	public List<PatientProfileEntity> searchPatient(Map<String, String> queryParam) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		List<PatientProfileEntity> patientProfile = patientDao.searchPatient(queryParam);
		return patientProfile;
	}

    public PatientProfileEntity getPatient(Long id) throws Exception {
        return patientDao.get(PatientProfileEntity.class, id);
    }

    public List<PatientProfileEntity> getDependentPatient(Long id) throws Exception {
        return patientDao.get(PatientProfileEntity.class, "parentPatient", id);
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
		addressEntity.setLandmark(addressWSO.getLandmark());
		addressEntity.setLatitude(addressWSO.getLatitude());
		addressEntity.setLongitude(addressWSO.getLongitude());
		addressEntity.setState(addressWSO.getState());
		addressEntity.setStreet(addressWSO.getStreet());
		addressEntity.setZipCode(addressWSO.getZipCode());
		patientProfileEntity.setAddress(addressEntity);
		}
		
		else
		{
			AddressEntity newAddressEntity=WSOToEntityConversion.transform(patientProfileWSO.getAddress());
			newAddressEntity.setId(null);
			patientProfileEntity.setAddress(newAddressEntity);
		}
		patientProfileEntity.setAge(patientProfileWSO.getAge());
		patientProfileEntity.setBloodGroup(WSOToEntityConversion.transform(patientProfileWSO.getBloodGroup()));
		patientProfileEntity.setDob(patientProfileWSO.getDob());
		patientProfileEntity.setEmergencyContacts(WSOToEntityConversion.transformContacts(patientProfileWSO.getEmergencyContacts()));
		patientProfileEntity.setGender(WSOToEntityConversion.transform(patientProfileWSO.getGender()));

		patientProfileEntity.setName(patientProfileWSO.getName());
		
		if( patientProfileWSO.getParentPatient()!=null && patientProfileWSO.getParentPatient().getId()!=null
				&& !patientProfileWSO.getParentPatient().getId().toString().isEmpty())
		{
			Long pateientId=patientProfileWSO.getParentPatient().getId();
			PatientProfileEntity parentPatientProfileEntity= patientDao.get(PatientProfileEntity.class, pateientId);
			patientProfileEntity.setParentPatient(parentPatientProfileEntity);
			
			patientProfileEntity.setRelationship(WSOToEntityConversion.transform(patientProfileWSO.getRelationship()));
		}
		else
		{
			patientProfileEntity.setParentPatient(null);
			patientProfileEntity.setRelationship(null);
		}
		
		Long userId= patientProfileWSO.getUser().getId();
		UserEntity userEntity = patientDao.get(UserEntity.class, userId);
		patientProfileEntity.setUser(userEntity);
		patientDao.save(patientProfileEntity);
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");
	
	}


    public void appointment(AppointmentWSO appointmentWSO, StatusWSO statusWSO) throws Exception {
        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setAppointmentOn(appointmentWSO.getAppointmentOn());
        appointment.setTime(appointmentWSO.getTime());
        ProfileEntity patient = new ProfileEntity();
        patient.setId(appointmentWSO.getPatient().getId());
        appointment.setPatient(patient);
        ProfileEntity doctor = new ProfileEntity();
        doctor.setId(appointmentWSO.getDoctor().getId());
        appointment.setDoctor(doctor);
        AccountEntity clinic = new AccountEntity();
        clinic.setId(appointmentWSO.getClinic().getId());
        appointment.setClinic(clinic);
        appointment.setToken(patientDao.getNextToken(appointmentWSO.getDoctor().getId(), appointmentWSO.getClinic().getId()));
        patientDao.save(appointment);
        /*TODO: Notification(Mail and SMS) for both doctor and patient has to add after successful completion of appointment*/
        saveSuccessMessage(statusWSO,"");
    }

    public void updateAppointmentStatus(Long appointmentId, String status, StatusWSO statusWSO) throws Exception {
        AppointmentEntity appointment = patientDao.get(AppointmentEntity.class, appointmentId);
        appointment.setStatus(AppointmentStatus.valueOf(status));
        patientDao.save(appointment);
        /*TODO: Notification(Mail and SMS) for both doctor and patient has to add after successful completion of appointment*/
        saveSuccessMessage(statusWSO,"");
    }

    public AppointmentWSO getAppointment(Long appointmentId){
        AppointmentEntity appointment = patientDao.getAppointment(appointmentId);
        if(null == appointment)
            throw new NoRecordFoundException("Noe record found for the given id="+ appointmentId);
        return DataMapper.transform(appointment);
    }

    public List<AppointmentWSO> getAppointments(Map<String,String> filterParam)throws Exception{
        List<AppointmentEntity> appointments = patientDao.getAppointments(filterParam);
        return DataMapper.transformAppointments(appointments);
    }
    
    
}
