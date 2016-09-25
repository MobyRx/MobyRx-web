package com.MobyRx.java.service.wso;

import com.MobyRx.java.entity.AddressEntity;
import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.entity.EmergencyContact;
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
public class DataMapper {


    public DrugWSO transform(DrugsEntity drugsEntity){
        DrugWSO drug = new DrugWSO();
        drug.setId(drugsEntity.getId());
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
    public static BloodGroup bloodGroupWSOToBloodGroupEntity(BloodGroupWSO bloodGroupWSO)
    {
    	 
    		    if( bloodGroupWSO.values().equals(BloodGroup.A_NEGATIVE) )
    		    	return BloodGroup.A_NEGATIVE;
    		    else if( bloodGroupWSO.values().equals(BloodGroup.A_POSITIVE) )
    		    	return BloodGroup.A_POSITIVE;
    		    else if( bloodGroupWSO.values().equals(BloodGroup.AB_NEGATIVE) )
		    	return BloodGroup.AB_NEGATIVE;
    		    else if( bloodGroupWSO.values().equals(BloodGroup.AB_POSITIVE) )
    		    	return BloodGroup.AB_POSITIVE;
    		    else if( bloodGroupWSO.values().equals(BloodGroup.B_NEGATIVE) )
    		    	return BloodGroup.B_NEGATIVE;
    		    else if( bloodGroupWSO.values().equals(BloodGroup.B_POSITIVE) )
    		    	return BloodGroup.B_POSITIVE;
    		    else if( bloodGroupWSO.values().equals(BloodGroup.O_NEGATIVE) )
    		    	return BloodGroup.O_NEGATIVE;
    		    else if( bloodGroupWSO.values().equals(BloodGroup.O_POSITIVE) )
    		    	return BloodGroup.O_POSITIVE;
				return null;
    		   
    		    
      }
    public static RelationshipType relationshipEntityWSOToRelationshipTypeEntity(RelationshipWSO relationshipWSO)
    {
    	 
    		    if( relationshipWSO.values().equals(RelationshipType.BROTHER) )
    		    	return RelationshipType.BROTHER;
    		    else if( relationshipWSO.values().equals(RelationshipType.CHILD) )
    		    	return RelationshipType.CHILD;
    		    else if( relationshipWSO.values().equals(RelationshipType.FATHER) )
    		    	return RelationshipType.FATHER;
    		    else if( relationshipWSO.values().equals(RelationshipType.MOTHER) )
    		    	return RelationshipType.MOTHER;
    		    else if( relationshipWSO.values().equals(RelationshipType.SISTER) )
    		    	return RelationshipType.SISTER;
    		    else if( relationshipWSO.values().equals(RelationshipType.WIFE) )
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
    if( genderWSO.values().equals(Gender.FEMALE) )
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
}
