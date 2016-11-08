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
import com.MobyRx.java.entity.type.DoseType;
import com.MobyRx.java.entity.type.DurationType;
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

	

	public static AddressEntity transform(AddressWSO addressWSO){
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

	public static ClinicCategoryEntity  transform(ClinicCategoryWSO clinicCategoryWSO)
	{
		ClinicCategoryEntity clinicCategoryEntity = new ClinicCategoryEntity();
		clinicCategoryEntity.setDescription(clinicCategoryWSO.getDescription());
		clinicCategoryEntity.setId(clinicCategoryWSO.getId());
		clinicCategoryEntity.setName(clinicCategoryWSO.getName());
		return clinicCategoryEntity;

	}
	public static ClinicEntity transform(ClinicWSO clinicWSO)
	{
		ClinicEntity clinicEntity = new ClinicEntity();

		clinicEntity.setAddress(transform(clinicWSO.getAddress()));
		clinicEntity.setCategory(transform(clinicWSO.getCategory()));
		clinicEntity.setServices(transformServices(clinicWSO.getServices()));

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

	public static List<ServiceEntity> transformServices(List<ServiceWSO> serviceWSO)
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
	public static BloodGroup transform(BloodGroupWSO bloodGroupWSO)
	{


		if( bloodGroupWSO.name().equals(BloodGroup.A_NEGATIVE.name()) )
			return BloodGroup.A_NEGATIVE;
		else if( bloodGroupWSO.name().equals(BloodGroup.A_POSITIVE.name()) )
			return BloodGroup.A_POSITIVE;
		else if( bloodGroupWSO.name().equals(BloodGroup.AB_NEGATIVE.name()) )
			return BloodGroup.AB_NEGATIVE;
		else if( bloodGroupWSO.name().equals(BloodGroup.AB_POSITIVE.name()) )
			return BloodGroup.AB_POSITIVE;
		else if( bloodGroupWSO.name().equals(BloodGroup.B_NEGATIVE.name()) )
			return BloodGroup.B_NEGATIVE;
		else if( bloodGroupWSO.name().equals(BloodGroup.B_POSITIVE.name()) )
			return BloodGroup.B_POSITIVE;
		else if( bloodGroupWSO.name().equals(BloodGroup.O_NEGATIVE.name()) )
			return BloodGroup.O_NEGATIVE;
		else if( bloodGroupWSO.name().equals(BloodGroup.O_POSITIVE.name()) )
			return BloodGroup.O_POSITIVE;
		return null;


	}
	public static RelationshipType transform(RelationshipWSO relationshipWSO)
	{

		if( relationshipWSO.name().equals(RelationshipType.BROTHER.name()) )
			return RelationshipType.BROTHER;
		else if( relationshipWSO.name().equals(RelationshipType.CHILD.name()) )
			return RelationshipType.CHILD;
		else if( relationshipWSO.name().equals(RelationshipType.FATHER.name()) )
			return RelationshipType.FATHER;
		else if( relationshipWSO.name().equals(RelationshipType.MOTHER.name()) )
			return RelationshipType.MOTHER;
		else if( relationshipWSO.name().equals(RelationshipType.SISTER.name()) )
			return RelationshipType.SISTER;
		else if( relationshipWSO.name().equals(RelationshipType.WIFE.name()) )
			return RelationshipType.WIFE;
		return null;
	}

	public static List<EmergencyContact> transformContacts(List<EmergencyContactWSO> emergencyContactsWSO){
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

	public static Gender transform(GenderWSO genderWSO){
		if(genderWSO.name().equals(Gender.FEMALE.name()))
		{
			return Gender.FEMALE;
		}
		else
			return Gender.MALE;
		

	} 
	public static DoseType transform(DoseWSO doseWSO){
		if(doseWSO.name().equals(DoseType._0_0_1.name()))
		{
			return DoseType._0_0_1;
		}else if(doseWSO.name().equals(DoseType._0_1_1.name()))
		{
			return DoseType._0_1_1;
		}
		else if(doseWSO.name().equals(DoseType._1_0_0.name()))
		{
			return DoseType._1_0_0;
		}
		else if(doseWSO.name().equals(DoseType._1_0_1.name()))
		{
			return DoseType._1_0_1;
		}
		else if(doseWSO.name().equals(DoseType._1_1_0.name()))
		{
			return DoseType._1_1_0;
		}
		else
		{
			return DoseType._1_1_1;
		}	

	} 	
	public static DurationType transform(DurationWSO durationWSO){
		 if(durationWSO.name().equals(DurationType.DAYS.name()))
		{
			return DurationType.DAYS;
		}else if(durationWSO.name().equals(DurationType.MONTH.name()))
		{
			return DurationType.MONTH;
		}else if(durationWSO.name().equals(DurationType.WEEK.name()))
		{
			return DurationType.WEEK;
		}else 
		{
			return DurationType.YEAR;
		}
		
	}
	public static Set<SpecializationEntity>  transformSpecializations(Set<SpecializationWSO> specializationsWSO){
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
	
	public static DrugsEntity transform(DrugWSO drugWSO)
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
		roleWSO.setCreatedAt(role.getCreatedAt());
		roleWSO.setDescription(role.getDescription());
		roleWSO.setId(role.getId());
		roleWSO.setName(role.getName());
		roleWSO.setUpdatedAt(role.getUpdatedAt());
		return roleWSO;
	}
}
