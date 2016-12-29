package com.MobyRx.java.service.wso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.MobyRx.java.entity.common.*;
import com.MobyRx.java.entity.doctor.DoctorProfileEntity;
import com.MobyRx.java.entity.doctor.DrugsEntity;
import com.MobyRx.java.entity.patient.PatientProfileEntity;
import com.MobyRx.java.entity.common.PrescriptionEntity;
import com.MobyRx.java.entity.common.PrescriptionItemEntity;
import com.MobyRx.java.entity.common.UserEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.MobyRx.java.entity.common.AccountEntity;
import com.MobyRx.java.entity.common.EmergencyContact;
import com.MobyRx.java.entity.master.RoleEntity;
import com.MobyRx.java.entity.master.ServiceEntity;
import com.MobyRx.java.entity.master.SpecializationEntity;
import com.MobyRx.java.entity.type.Gender;

public class DataMapper {
	private  static Logger logger = LoggerFactory.getLogger(DataMapper.class);

    public static PatientProfileWSO transform(PatientProfileEntity patientProfile){
        PatientProfileWSO patientProfileWSO = new PatientProfileWSO();
        patientProfileWSO.setId(patientProfile.getId());
        patientProfileWSO.setAge(patientProfile.getAge());
        patientProfileWSO.setBloodGroup(BloodGroupWSO.valueOf(patientProfile.getBloodGroup().name()));
        patientProfileWSO.setDob(patientProfile.getDob());
        if(patientProfile.getRelationship()!=null)
        patientProfileWSO.setRelationship(RelationshipWSO.valueOf(patientProfile.getRelationship().name()));
        patientProfileWSO.setName(patientProfile.getName());
        patientProfileWSO.setGender(GenderWSO.valueOf(patientProfile.getGender().name()));
        patientProfileWSO.setAddress(transform(patientProfile.getAddress()));
        //patientProfileWSO.setEmergencyContacts(transform(patientProfile.getEmergencyContacts()));
        if(patientProfile.getParentPatient()!=null)
        patientProfileWSO.setParentPatient(transform(patientProfile.getParentPatient()));
        patientProfileWSO.setCreatedAt(patientProfile.getCreatedAt());
        patientProfileWSO.setUser(transform(patientProfile.getUser()));
        return patientProfileWSO;
    }

    public static List<DrugWSO> transformDrugs(List<DrugsEntity> drugsEntities){
		List<DrugWSO> drugs = new ArrayList<DrugWSO>();
		for(DrugsEntity drugsEntity : drugsEntities)
			drugs.add(transform(drugsEntity));
		return drugs;
	}
    public static List<PatientProfileWSO> transformPatients(List<PatientProfileEntity> patientProfiles){
        List<PatientProfileWSO> patientProfileWSOs = new ArrayList<PatientProfileWSO>();
        for(PatientProfileEntity patientProfileEntity : patientProfiles)
            patientProfileWSOs.add(transform(patientProfileEntity));
        return patientProfileWSOs;
    }
    
    public static List<DoctorProfileWSO> transformDoctors(List<DoctorProfileEntity> doctorProfiles){
        List<DoctorProfileWSO> doctorProfileWSOs = new ArrayList<DoctorProfileWSO>();
        for(DoctorProfileEntity doctorProfileEntity : doctorProfiles)
        	doctorProfileWSOs.add(transform(doctorProfileEntity));
        return doctorProfileWSOs;
    }
    
    
    
	public static DoctorProfileWSO transform(DoctorProfileEntity doctorProfileEntity) {
		DoctorProfileWSO doctorProfileWSO = new DoctorProfileWSO();
		doctorProfileWSO.setAchievements(doctorProfileEntity.getAchievements());
		doctorProfileWSO.setAddress(transform(doctorProfileEntity.getAddress()));
		doctorProfileWSO.setCertificateNumber(doctorProfileEntity.getCertificateNumber());
		doctorProfileWSO.setCertification(doctorProfileEntity.getCertification());
		doctorProfileWSO.setClinic(transform(doctorProfileEntity.getAccount()));
		doctorProfileWSO.setCreatedAt(doctorProfileEntity.getCreatedAt());
		doctorProfileWSO.setUpdatedAt(doctorProfileEntity.getUpdatedAt());
		//doctorProfileWSO.setEmergencyContacts(transform(doctorProfileEntity.getEmergencyContacts()));
		doctorProfileWSO.setGender(transform(doctorProfileEntity.getGender()));
		doctorProfileWSO.setId(doctorProfileEntity.getId());
		doctorProfileWSO.setMedRegNumber(doctorProfileEntity.getMedRegNumber());
		doctorProfileWSO.setName(doctorProfileEntity.getName());
		doctorProfileWSO.setPracticeStartAt(doctorProfileEntity.getPracticeStartAt());
		doctorProfileWSO.setQualification(doctorProfileEntity.getQualification());
		doctorProfileWSO.setSpecializations(transformSpecialization(doctorProfileEntity.getSpecializations()));
		doctorProfileWSO.setUser(transform(doctorProfileEntity.getUser()));
		doctorProfileWSO.setVerified(doctorProfileEntity.isVerified());
		return doctorProfileWSO;
	}
	
	
	public static List<EmergencyContactWSO> transform(List<EmergencyContact> emergencyContacts){
		List<EmergencyContactWSO> emergencyContactsWSO= new ArrayList<EmergencyContactWSO>();

		for(int i=0; i<emergencyContacts.size();i++)
		{
			EmergencyContactWSO emergencyContactWSO = new EmergencyContactWSO();
			emergencyContactWSO.setMobile(emergencyContacts.get(i).getMobile());
			emergencyContactWSO.setName(emergencyContacts.get(i).getName());
			emergencyContactsWSO.add(emergencyContactWSO);
		}

		return emergencyContactsWSO;
	}
	public static GenderWSO transform(Gender gender){
		if(gender.name().equals(GenderWSO.FEMALE.name()))
		{
			return GenderWSO.FEMALE;
		}
		else
			return GenderWSO.MALE;
		

	}
	public static Set<SpecializationWSO>  transformSpecialization(Set<SpecializationEntity> specializations){
		Set<SpecializationWSO> specializationsWSO= new HashSet<SpecializationWSO>();

		for (Iterator<SpecializationEntity> it = specializations.iterator(); it.hasNext(); ) {
			SpecializationEntity  specialization= it.next();

			SpecializationWSO specializationWSO=  new SpecializationWSO();
			specializationWSO.setDescription(specialization.getDescription());
			specializationWSO.setId(specialization.getId());
			specializationWSO.setName(specialization.getName());
			specializationsWSO.add(specializationWSO);


		}

		return specializationsWSO;
	} 
	public static UserWSO transform(UserEntity userEntity)
	{
		UserWSO userWSO = new UserWSO();
		userWSO.setCreatedAt(userEntity.getCreatedAt());
		userWSO.setEmail(userEntity.getEmail());
		userWSO.setMobile(userEntity.getMobile());
		userWSO.setEmailVerified(userEntity.isEmailVerified());
		userWSO.setMobileVerified(userEntity.isMobileVerified());
		userWSO.setPassword(userEntity.getPassword());
		userWSO.setUpdatedAt(userEntity.getUpdatedAt());
		//userWSO.setUsername(userEntity.getUsername());
		userWSO.setId(userEntity.getId());
		userWSO.setRoles(transformRoles(userEntity.getRoles()));
		
		return userWSO;
		
	}
	private static Set<String> transformRoles(Set<RoleEntity> roles) {
		 Set<String> RolesWSO = new HashSet<String>();
	        for(RoleEntity roleEntity : roles)
	        	RolesWSO.add(roleEntity.getName());
	        return RolesWSO;
	}

	public static RoleWSO transform(RoleEntity role){
		RoleWSO roleWSO = new RoleWSO();
		//roleWSO.setCreatedAt(role.getCreatedAt());
		roleWSO.setDescription(role.getDescription());
		roleWSO.setId(role.getId());
		roleWSO.setName(role.getName());
		//roleWSO.setUpdatedAt(role.getUpdatedAt());
		return roleWSO;
	}
	private static Set<ClinicWSO> transform(Set<AccountEntity> account) {
		Set<ClinicWSO> cilincSet = new HashSet<ClinicWSO>();
		for(AccountEntity accountEntity : account)
			cilincSet.add(transform(accountEntity));
		return cilincSet;
	}
	
	public static AddressWSO transform(AddressEntity addressEntity) {
		AddressWSO 	addressWSO=  new AddressWSO();
		addressWSO.setBuildingNumber(addressEntity.getBuildingNumber());
		addressWSO.setCity(addressEntity.getCity());
		addressWSO.setCountry(addressEntity.getCountry());
		addressWSO.setCreatedAt(addressEntity.getCreatedAt());
		addressWSO.setId(addressEntity.getId());
		addressWSO.setLandmark(addressEntity.getLandmark());
		addressWSO.setLatitude(addressEntity.getLatitude());
		addressWSO.setLongitude(addressEntity.getLongitude());
		addressWSO.setState(addressEntity.getState());
		addressWSO.setStreet(addressEntity.getStreet());
		addressWSO.setUpdatedAt(addressEntity.getUpdatedAt());
		addressWSO.setZipCode(addressEntity.getZipCode());

		return addressWSO;
	}
	public static ClinicWSO transform(AccountEntity accountEntity) {
		ClinicWSO clinic = new ClinicWSO();
		clinic.setAddress(transform(accountEntity.getAddress()));

		List<ServiceWSO> serviceEntityList= new ArrayList<ServiceWSO>();

		for (ServiceEntity service : accountEntity.getServices()) {
			ServiceWSO serviceEntity = new ServiceWSO();
			serviceEntity.setDescription(service.getDescription());
			serviceEntity.setName(service.getName());
			serviceEntity.setId(service.getId());
			serviceEntityList.add(serviceEntity);

		}

		clinic.setServices(serviceEntityList);

		ClinicCategoryWSO clinicCategoryWSO = new ClinicCategoryWSO();
		clinicCategoryWSO.setDescription(accountEntity.getCategory().getDescription());
		clinicCategoryWSO.setId(accountEntity.getCategory().getId());
		clinicCategoryWSO.setName(accountEntity.getCategory().getName());
		clinic.setCategory(clinicCategoryWSO);

		clinic.setCreatedAt(accountEntity.getCreatedAt());
		clinic.setEmail(accountEntity.getEmail());
		clinic.setId(accountEntity.getId());
		clinic.setLicenceNumber(accountEntity.getLicenceNumber());
		clinic.setName(accountEntity.getName());

		clinic.setPhoneNumber(accountEntity.getPhoneNumber());
		clinic.setRegistrationDate(accountEntity.getRegistrationDate());
		clinic.setUpdatedAt(accountEntity.getUpdatedAt());
		clinic.setUrl(accountEntity.getUrl());
		clinic.setVerified(accountEntity.isVerified());


		return clinic;
	}

	
	
	

	public static DrugWSO transform(DrugsEntity drugs) {
		DrugWSO drugWSO= new DrugWSO();
		drugWSO.setBrandName(drugs.getBrandName());
		drugWSO.setConstituent(drugs.getConstituent());
		drugWSO.setCreatedAt(drugs.getCreatedAt());
		drugWSO.setDrugType(drugs.getDrugType());
		drugWSO.setId(drugs.getId());
		drugWSO.setManufacturer(drugs.getManufacturer());
		drugWSO.setMeasurementUnit(drugs.getMeasurementUnit());
		drugWSO.setName(drugs.getName());
		drugWSO.setPackageUnit(drugs.getPackageUnit());
		drugWSO.setPrice(drugs.getPrice());
		drugWSO.setUpdatedAt(drugs.getUpdatedAt());
		
		return drugWSO;
	}

	public static ProfileWSO transform(ProfileEntity profile) {
		ProfileWSO profileWSO = new ProfileWSO();
		if(profile.getAddress()!=null)
		profileWSO.setAddress(transform(profile.getAddress()));
		profileWSO.setCreatedAt(profile.getCreatedAt());
		//profileWSO.setEmergencyContacts(transform(profile.getEmergencyContacts()));
		profileWSO.setGender(GenderWSO.valueOf(profile.getGender().name()));
		profileWSO.setId(profile.getId());
		profileWSO.setName(profile.getName());
		profileWSO.setUpdatedAt(profile.getUpdatedAt());
		profileWSO.setUser(transform(profile.getUser()));
		return profileWSO;
	}

	
}
