package com.MobyRx.java.service.wso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.MobyRx.java.entity.PatientProfileEntity;
import com.MobyRx.java.entity.PrescriptionEntity;
import com.MobyRx.java.entity.PrescriptionItemEntity;
import com.MobyRx.java.entity.ProfileEntity;
import com.MobyRx.java.entity.UserEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.MobyRx.java.entity.AddressEntity;
import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.entity.DoctorProfileEntity;
import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.entity.EmergencyContact;
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
        patientProfileWSO.setUpdatedAt(patientProfile.getUpdatedAt());
        patientProfileWSO.setCreatedAt(patientProfile.getCreatedAt());
        patientProfileWSO.setUser(transform(patientProfile.getUser()));
        return patientProfileWSO;
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
    
    
    
	private static DoctorProfileWSO transform(DoctorProfileEntity doctorProfileEntity) {
		DoctorProfileWSO doctorProfileWSO = new DoctorProfileWSO();
		doctorProfileWSO.setAchievements(doctorProfileEntity.getAchievements());
		doctorProfileWSO.setAddress(transform(doctorProfileEntity.getAddress()));
		doctorProfileWSO.setCertificateNumber(doctorProfileEntity.getCertificateNumber());
		doctorProfileWSO.setCertification(doctorProfileEntity.getCertification());
		doctorProfileWSO.setClinic(transform(doctorProfileEntity.getClinic()));
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
		userWSO.setUsername(userEntity.getUsername());
		userWSO.setId(userEntity.getId());
		userWSO.setRoles(transformRoles(userEntity.getRoles()));
		
		return userWSO;
		
	}
	private static Set<RoleWSO> transformRoles(Set<RoleEntity> roles) {
		 Set<RoleWSO> RolesWSO = new HashSet<RoleWSO>();
	        for(RoleEntity roleEntity : roles)
	        	RolesWSO.add(transform(roleEntity));
	        return RolesWSO;
	}

	public static RoleWSO transform(RoleEntity role){
		RoleWSO roleWSO = new RoleWSO();
		roleWSO.setCreatedAt(role.getCreatedAt());
		roleWSO.setDescription(role.getDescription());
		roleWSO.setId(role.getId());
		roleWSO.setName(role.getName());
		roleWSO.setUpdatedAt(role.getUpdatedAt());
		return roleWSO;
	}
	private static Set<ClinicWSO> transform(Set<ClinicEntity> clinic) {
		Set<ClinicWSO> cilincSet = new HashSet<ClinicWSO>();
		for(ClinicEntity clinicEntity : clinic)
			cilincSet.add(transform(clinicEntity));
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
	public static ClinicWSO transform(ClinicEntity clinicEntity) {
		ClinicWSO clinic = new ClinicWSO();
		clinic.setAddress(transform(clinicEntity.getAddress()));

		List<ServiceWSO> serviceEntityList= new ArrayList<ServiceWSO>();

		for (ServiceEntity service : clinicEntity.getServices()) {
			ServiceWSO serviceEntity = new ServiceWSO();
			serviceEntity.setDescription(service.getDescription());
			serviceEntity.setName(service.getName());
			serviceEntity.setId(service.getId());
			serviceEntityList.add(serviceEntity);

		}

		clinic.setServices(serviceEntityList);

		ClinicCategoryWSO clinicCategoryWSO = new ClinicCategoryWSO();
		clinicCategoryWSO.setDescription(clinicEntity.getCategory().getDescription());
		clinicCategoryWSO.setId(clinicEntity.getCategory().getId());
		clinicCategoryWSO.setName(clinicEntity.getCategory().getName());
		clinic.setCategory(clinicCategoryWSO);

		clinic.setCreatedAt(clinicEntity.getCreatedAt());
		clinic.setEmail(clinicEntity.getEmail());
		clinic.setId(clinicEntity.getId());
		clinic.setLicenceNumber(clinicEntity.getLicenceNumber());
		clinic.setName(clinicEntity.getName());

		clinic.setPhoneNumber(clinicEntity.getPhoneNumber());
		clinic.setRegistrationDate(clinicEntity.getRegistrationDate());
		clinic.setUpdatedAt(clinicEntity.getUpdatedAt());
		clinic.setUrl(clinicEntity.getUrl());
		clinic.setVerified(clinicEntity.isVerified());


		return clinic;
	}

	public static List<PrescriptionWSO> transformPrescription(List<PrescriptionEntity> prescriptionEntitys) {
		List<PrescriptionWSO> prescriptionWSOList = new ArrayList<PrescriptionWSO>();
		
		for (int i=0;i<prescriptionEntitys.size();i++) {
			PrescriptionWSO prescriptionWSO = new PrescriptionWSO();
			prescriptionWSO.setClinic(transform(prescriptionEntitys.get(i).getClinic()));
			prescriptionWSO.setCreatedAt(prescriptionEntitys.get(i).getCreatedAt());
			if(prescriptionEntitys.get(i).getDoctor()!=null)
			prescriptionWSO.setDoctor(transform(prescriptionEntitys.get(i).getDoctor()));
			prescriptionWSO.setId(prescriptionEntitys.get(i).getId());
			prescriptionWSO.setInstruction(prescriptionEntitys.get(i).getInstruction());
			prescriptionWSO.setNextAppointment(prescriptionEntitys.get(i).getNextAppointment());
			if(prescriptionEntitys.get(i).getPatient()!=null)
			prescriptionWSO.setPatient(transform(prescriptionEntitys.get(i).getPatient()));
			
			prescriptionWSO.setPrescriptionNumber(prescriptionEntitys.get(i).getPrescriptionNumber());
			prescriptionWSO.setUpdatedAt(prescriptionEntitys.get(i).getUpdatedAt());
			
			Set<PrescriptionItemWSO> prescriptionItemWSOSet =new HashSet<PrescriptionItemWSO>();
			Set<PrescriptionItemEntity> prescriptionItemEntitySet =prescriptionEntitys.get(i).getPrescriptionItems();
			for (Iterator<PrescriptionItemEntity> it = prescriptionItemEntitySet.iterator(); it.hasNext(); ) {
				PrescriptionItemEntity  prescriptionItemEntity= it.next();
				PrescriptionItemWSO prescriptionItemWSO = new PrescriptionItemWSO();
				prescriptionItemWSO.setBeforeFood(prescriptionItemEntity.isBeforeFood());
				prescriptionItemWSO.setCreatedAt(prescriptionItemEntity.getCreatedAt());
				prescriptionItemWSO.setDoseType(DoseWSO.valueOf(prescriptionItemEntity.getDoseType().name()));
				prescriptionItemWSO.setDrugName(prescriptionItemEntity.getDrugName());
				prescriptionItemWSO.setDrugs(transform(prescriptionItemEntity.getDrugs()));
				prescriptionItemWSO.setDuration(prescriptionItemEntity.getDuration());
				prescriptionItemWSO.setDurationType(DurationWSO.valueOf(prescriptionItemEntity.getDurationType().name()));
				prescriptionItemWSO.setId(prescriptionItemEntity.getId());
				prescriptionItemWSO.setInstruction(prescriptionItemEntity.getInstruction());
				prescriptionItemWSO.setPrescription(transform(prescriptionItemEntity.getPrescription()));
				prescriptionItemWSO.setQuantity(prescriptionItemEntity.getQuantity());
				prescriptionItemWSO.setUpdatedAt(prescriptionItemEntity.getCreatedAt());
				
				prescriptionItemWSOSet.add(prescriptionItemWSO);
			
			}
			
			prescriptionWSO.setPrescriptionItems(prescriptionItemWSOSet);
			
			prescriptionWSOList.add(prescriptionWSO);
		}
		
		return prescriptionWSOList;
		
		
	}

	private static PrescriptionWSO transform(PrescriptionEntity prescription) {
		PrescriptionWSO prescriptionWSO = new PrescriptionWSO();
		prescriptionWSO.setClinic(transform(prescription.getClinic()));
		prescriptionWSO.setCreatedAt(prescription.getCreatedAt());
		prescriptionWSO.setDoctor(transform(prescription.getDoctor()));
		prescriptionWSO.setId(prescription.getId());
		prescriptionWSO.setInstruction(prescription.getInstruction());
		prescriptionWSO.setNextAppointment(prescription.getNextAppointment());
		prescriptionWSO.setPatient(transform(prescription.getPatient()));
		prescriptionWSO.setPrescriptionItems(null);
		prescriptionWSO.setPrescriptionNumber(prescription.getPrescriptionNumber());
		prescriptionWSO.setUpdatedAt(prescription.getUpdatedAt());
	
		return prescriptionWSO;
	}

	
	

	private static DrugWSO transform(DrugsEntity drugs) {
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

	private static ProfileWSO transform(ProfileEntity profile) {
		ProfileWSO profileWSO = new ProfileWSO();
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
