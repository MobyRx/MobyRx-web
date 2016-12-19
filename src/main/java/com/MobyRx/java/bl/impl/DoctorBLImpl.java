package com.MobyRx.java.bl.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.MobyRx.java.entity.common.AccountEntity;
import com.MobyRx.java.entity.common.AddressEntity;
import com.MobyRx.java.entity.common.UserEntity;
import com.MobyRx.java.entity.doctor.DoctorProfileEntity;
import com.MobyRx.java.exception.NoRecordFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.MobyRx.java.bl.DoctorBL;
import com.MobyRx.java.dao.DoctorDao;
import com.MobyRx.java.entity.master.SpecializationEntity;
import com.MobyRx.java.service.wso.ClinicWSO;
import com.MobyRx.java.service.wso.DoctorProfileWSO;
import com.MobyRx.java.service.wso.SpecializationWSO;
import com.MobyRx.java.service.wso.StatusWSO;
import com.MobyRx.java.service.wso.WSOToEntityConversion;

@Repository("doctorBL")
@Transactional
public class DoctorBLImpl extends CommonBLImpl implements DoctorBL {

    private Logger logger = LoggerFactory.getLogger(DoctorBLImpl.class);

    @Autowired
    private DoctorDao doctorDao;

    public void save(DoctorProfileWSO doctorProfileWSO, StatusWSO statusWSO) throws Exception {
        DoctorProfileEntity doctorProfileEntity = new DoctorProfileEntity();
        doctorProfileEntity.setAchievements(doctorProfileWSO.getAchievements());
        AddressEntity addressEntity = WSOToEntityConversion.transform(doctorProfileWSO.getAddress());
        doctorProfileEntity.setAddress(addressEntity);
        doctorProfileEntity.setCertificateNumber(doctorProfileWSO.getCertificateNumber());
        doctorProfileEntity.setCertification(doctorProfileWSO.getCertification());
        if (doctorProfileWSO.getClinic().size() > 0) {
            doctorProfileEntity.setAccount(null);
            Set<AccountEntity> accountEntityList = new HashSet<AccountEntity>();
            for (ClinicWSO clinicWSO : doctorProfileWSO.getClinic()) {
                logger.info("clinicWSO.getId()=" + clinicWSO.getId());
                AccountEntity accountEntity = doctorDao.get(AccountEntity.class, clinicWSO.getId());
                accountEntityList.add(accountEntity);
            }
            doctorProfileEntity.setAccount(accountEntityList);
        }
        doctorProfileEntity.setEmergencyContacts(WSOToEntityConversion.transformContacts(doctorProfileWSO.getEmergencyContacts()));
        doctorProfileEntity.setGender(WSOToEntityConversion.transform(doctorProfileWSO.getGender()));
        doctorProfileEntity.setMedRegNumber(doctorProfileWSO.getMedRegNumber());
        doctorProfileEntity.setName(doctorProfileWSO.getName());
        doctorProfileEntity.setPracticeStartAt(doctorProfileWSO.getPracticeStartAt());
        doctorProfileEntity.setQualification(doctorProfileWSO.getQualification());
        Set<SpecializationEntity> specializationEntityList = new HashSet<SpecializationEntity>();
        if (doctorProfileWSO.getSpecializations().size() > 0) {
            for (SpecializationWSO specializationWSO : doctorProfileWSO.getSpecializations()) {
                SpecializationEntity SpecializationEntity = doctorDao.get(SpecializationEntity.class, specializationWSO.getId());
                specializationEntityList.add(SpecializationEntity);
            }
            doctorProfileEntity.setSpecializations(specializationEntityList);
        } else {
            doctorProfileEntity.setSpecializations(null);
        }
        Long userId = doctorProfileWSO.getUser().getId();
        UserEntity userEntity = (UserEntity) doctorDao.get(UserEntity.class, userId);
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
		addressEntity.setLandmark(doctorProfileWSO.getAddress().getLandmark());
		addressEntity.setLatitude(doctorProfileWSO.getAddress().getLatitude());
		addressEntity.setLongitude(doctorProfileWSO.getAddress().getLongitude());
		addressEntity.setState(doctorProfileWSO.getAddress().getState());
		addressEntity.setStreet(doctorProfileWSO.getAddress().getStreet());
		addressEntity.setZipCode(doctorProfileWSO.getAddress().getZipCode());
		doctorProfileEntity.setAddress(addressEntity);
		}
		
		else
		{
			AddressEntity newAddressEntity=WSOToEntityConversion.transform(doctorProfileWSO.getAddress());
			newAddressEntity.setId(null);
			doctorProfileEntity.setAddress(newAddressEntity);
		}
		doctorProfileEntity.setCertificateNumber(doctorProfileWSO.getCertificateNumber());
		doctorProfileEntity.setCertification(doctorProfileWSO.getCertification());
		if(doctorProfileWSO.getClinic().size()>0)
		{
			Set<AccountEntity> accountEntityList = doctorProfileEntity.getAccount();
			for(ClinicWSO clinicWSO : doctorProfileWSO.getClinic())
			{
				logger.info("clinicWSO.getId()="+clinicWSO.getId());
				AccountEntity accountEntity = doctorDao.get(AccountEntity.class, clinicWSO.getId());
				if(accountEntity ==null)
				{
					AccountEntity newAccountEntity = WSOToEntityConversion.transform(clinicWSO);
					accountEntityList.add(newAccountEntity);
					
				}
				else
				{
				accountEntityList.add(accountEntity);
				}
			}
			doctorProfileEntity.setAccount(accountEntityList);
		}
		doctorProfileEntity.setEmergencyContacts(WSOToEntityConversion.transformContacts(doctorProfileWSO.getEmergencyContacts()));
		doctorProfileEntity.setGender(WSOToEntityConversion.transform(doctorProfileWSO.getGender())); doctorProfileEntity.getGender();
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
		Long userId= doctorProfileWSO.getUser().getId();
		UserEntity userEntity = (UserEntity)doctorDao.get(UserEntity.class, userId);
		doctorProfileEntity.setUser(userEntity);
		doctorProfileEntity.setVerified(doctorProfileWSO.isVerified());
		doctorDao.save(doctorProfileEntity);
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");

	
    }

    public List<DoctorProfileEntity> searchDoctor(Map<String,String> filterParam) throws Exception {
    	List<DoctorProfileEntity> doctorProfile = doctorDao.searchDoctor(filterParam);
		return doctorProfile;
    }


    public DoctorProfileEntity get(Long id) {
        DoctorProfileEntity doctorProfile = (DoctorProfileEntity)doctorDao.get(DoctorProfileEntity.class, id);
        if(null== doctorProfile)
            throw new NoRecordFoundException("No Doctor profile found for this id:-"+ id);
        return doctorProfile;
    }

    public void deleteDoctor(Long id) {
        this.doctorDao.softDelete(DoctorProfileEntity.class, id);
    }

}
