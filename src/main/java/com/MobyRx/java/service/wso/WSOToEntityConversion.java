package com.MobyRx.java.service.wso;

import com.MobyRx.java.entity.AddressEntity;
import com.MobyRx.java.entity.BaseEntity;
import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.entity.EmergencyContact;
import com.MobyRx.java.entity.master.ClinicCategoryEntity;
import com.MobyRx.java.entity.master.RoleEntity;
import com.MobyRx.java.entity.master.ServiceEntity;
import com.MobyRx.java.entity.master.SpecializationEntity;
import com.MobyRx.java.entity.type.BloodGroup;
import com.MobyRx.java.entity.type.Gender;
import com.MobyRx.java.entity.type.RelationshipType;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/14/16
 * Time: 11:35 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class WSOToEntityConversion {


	public DrugWSO transform(DrugsEntity drugsEntity){
		DrugWSO drug = new DrugWSO();
		drug.setId(drugsEntity.getId());
		drug.setBrandName(drugsEntity.getBrandName());
		drug.setConstituent(drugsEntity.getConstituent());
		drug.setCreatedAt(drugsEntity.getCreatedAt());
		drug.setDrugType(drugsEntity.getDrugType());
		drug.setManufacturer(drugsEntity.getManufacturer());
		drug.setPackageUnit(drugsEntity.getPackageUnit());
		drug.setPrice(drugsEntity.getPrice());
		drug.setUpdatedAt(drugsEntity.getUpdatedAt());
		drug.setName(drugsEntity.getName());
		return drug;
	}

	public List<DrugWSO> transform(List<DrugsEntity> drugsEntities){
		List<DrugWSO> drugs = new ArrayList<DrugWSO>();
		for(DrugsEntity drugsEntity : drugsEntities)
			drugs.add(transform(drugsEntity));
		return drugs;
	}

	public static AddressEntity addressWSOToAddressEntity(AddressWSO addressWSO){
		AddressEntity addressEntity= new AddressEntity();
		addressEntity.setBuildingNumber(addressWSO.getBuildingNumber());
		addressEntity.setCity(addressWSO.getCity());
		addressEntity.setCountry(addressWSO.getCountry());
		addressEntity.setCreatedAt(addressWSO.getCreatedAt());
		addressEntity.setId(addressWSO.getId());
		addressEntity.setLandmark(addressWSO.getLandmark());
		addressEntity.setLatitude(addressWSO.getLatitude());
		addressEntity.setLongitude(addressWSO.getLongitude());
		addressEntity.setState(addressWSO.getState());
		addressEntity.setStreet(addressWSO.getStreet());
		addressEntity.setUpdatedAt(addressWSO.getUpdatedAt());
		addressEntity.setZipCode(addressWSO.getZipCode());

		return addressEntity;
	}

	public static ClinicCategoryEntity  clinicCategoryEntityToClinicCategoryWSO(ClinicCategoryWSO clinicCategoryWSO)
	{
		ClinicCategoryEntity clinicCategoryEntity = new ClinicCategoryEntity();
		clinicCategoryEntity.setDescription(clinicCategoryWSO.getDescription());
		clinicCategoryEntity.setId(clinicCategoryWSO.getId());
		clinicCategoryEntity.setName(clinicCategoryWSO.getName());
		return clinicCategoryEntity;

	}
	public static ClinicEntity ClinicWSOToClinicEntity(ClinicWSO clinicWSO)
	{
		ClinicEntity clinicEntity = new ClinicEntity();

		clinicEntity.setAddress(addressWSOToAddressEntity(clinicWSO.getAddress()));
		clinicEntity.setCategory(null);
		clinicEntity.setServices(null);

		clinicEntity.setCreatedAt(clinicWSO.getCreatedAt());
		clinicEntity.setEmail(clinicWSO.getEmail());
		clinicEntity.setId(clinicWSO.getId());
		clinicEntity.setLicenceNumber(clinicWSO.getLicenceNumber());
		clinicEntity.setName(clinicWSO.getName());
		clinicEntity.setPhoneNumber(clinicWSO.getPhoneNumber());
		clinicEntity.setRegistrationDate(clinicWSO.getRegistrationDate());
		clinicEntity.setUpdatedAt(clinicWSO.getUpdatedAt());
		clinicEntity.setUrl(clinicWSO.getUrl());
		clinicEntity.setVerified(clinicWSO.isVerified());;
		return clinicEntity;

	}

	public static List<ServiceEntity>  serviceEntityToServiceWSO(List<ServiceWSO> serviceWSO)
	{
		List<ServiceEntity> serviceEntityList= new ArrayList<ServiceEntity>();
		for (ServiceWSO service : serviceWSO) {
			ServiceEntity serviceEntity = new ServiceEntity();
			serviceEntity.setDescription(service.getDescription());
			serviceEntity.setName(service.getName());
			serviceEntityList.add(serviceEntity);

		}
		return serviceEntityList;
	}
	public static BloodGroup bloodGroupWSOToBloodGroupEntity(BloodGroupWSO bloodGroupWSO)
	{


		if( bloodGroupWSO.equals(BloodGroup.A_NEGATIVE) )
			return BloodGroup.A_NEGATIVE;
		else if( bloodGroupWSO.equals(BloodGroup.A_POSITIVE) )
			return BloodGroup.A_POSITIVE;
		else if( bloodGroupWSO.equals(BloodGroup.AB_NEGATIVE) )
			return BloodGroup.AB_NEGATIVE;
		else if( bloodGroupWSO.equals(BloodGroup.AB_POSITIVE) )
			return BloodGroup.AB_POSITIVE;
		else if( bloodGroupWSO.equals(BloodGroup.B_NEGATIVE) )
			return BloodGroup.B_NEGATIVE;
		else if( bloodGroupWSO.equals(BloodGroup.B_POSITIVE) )
			return BloodGroup.B_POSITIVE;
		else if( bloodGroupWSO.equals(BloodGroup.O_NEGATIVE) )
			return BloodGroup.O_NEGATIVE;
		else if( bloodGroupWSO.equals(BloodGroup.O_POSITIVE) )
			return BloodGroup.O_POSITIVE;
		return null;


	}
	public static RelationshipType relationshipEntityWSOToRelationshipTypeEntity(RelationshipWSO relationshipWSO)
	{

		if( relationshipWSO.equals(RelationshipType.BROTHER) )
			return RelationshipType.BROTHER;
		else if( relationshipWSO.equals(RelationshipType.CHILD) )
			return RelationshipType.CHILD;
		else if( relationshipWSO.equals(RelationshipType.FATHER) )
			return RelationshipType.FATHER;
		else if( relationshipWSO.equals(RelationshipType.MOTHER) )
			return RelationshipType.MOTHER;
		else if( relationshipWSO.equals(RelationshipType.SISTER) )
			return RelationshipType.SISTER;
		else if( relationshipWSO.equals(RelationshipType.WIFE) )
			return RelationshipType.WIFE;
		return null;
	}

	public static List<EmergencyContact> emergencyContactToEmergencyContactEntity(List<EmergencyContactWSO> emergencyContactsWSO){
		List<EmergencyContact> emergencyContacts= new ArrayList<EmergencyContact>();

		for(int i=0; i<emergencyContactsWSO.size();i++)
		{
			EmergencyContact emergencyContact = new EmergencyContact();
			emergencyContact.setMobile(emergencyContactsWSO.get(i).getMobile());
			emergencyContact.setName(emergencyContactsWSO.get(i).getName());
			emergencyContacts.add(emergencyContact);
		}

		return emergencyContacts;
	}

	public static Gender genderWSOTOGenderEntity(GenderWSO genderWSO){
		if( genderWSO.equals(Gender.FEMALE) )
			return Gender.FEMALE;
		else
			return Gender.MALE;

	} 	 
	public static Set<SpecializationEntity>  specializationWSOToSpecializationEntity(Set<SpecializationWSO> specializationsWSO){
		Set<SpecializationEntity> specializationEntitys= new HashSet<SpecializationEntity>();

		for (Iterator<SpecializationWSO> it = specializationsWSO.iterator(); it.hasNext(); ) {
			SpecializationWSO  specialization= it.next();

			SpecializationEntity specializationEntity=  new SpecializationEntity();
			specializationEntity.setDescription(specialization.getDescription());
			specializationEntity.setId(specialization.getId());
			specializationEntity.setName(specialization.getName());
			specializationEntitys.add(specializationEntity);


		}

		return specializationEntitys;

	} 
	
	public static DrugsEntity drugWSOToDrugsEntity(DrugWSO drugWSO)
	{
		DrugsEntity drugEntity = new DrugsEntity();
		drugEntity.setName(drugWSO.getName());
		drugEntity.setBrandName(drugWSO.getBrandName());
		drugEntity.setConstituent(drugWSO.getConstituent());
		drugEntity.setCreatedAt(drugWSO.getCreatedAt());
		drugEntity.setDrugType(drugWSO.getDrugType());
		drugEntity.setId(drugWSO.getId());
		drugEntity.setManufacturer(drugWSO.getManufacturer());
		drugEntity.setMeasurementUnit(drugWSO.getMeasurementUnit());
		drugEntity.setPackageUnit(drugWSO.getPackageUnit());
		drugEntity.setPrice(drugWSO.getPrice());
		drugEntity.setUpdatedAt(drugWSO.getUpdatedAt());
		return drugEntity;
	}
	
	
	public static RoleWSO transform(RoleEntity role){
		RoleWSO roleWSO = new RoleWSO();
		return roleWSO;
	}
}
