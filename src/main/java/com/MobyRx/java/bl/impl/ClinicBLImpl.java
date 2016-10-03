package com.MobyRx.java.bl.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.MobyRx.java.bl.ClinicBL;
import com.MobyRx.java.dao.ClinicDao;
import com.MobyRx.java.dao.DoctorDao;
import com.MobyRx.java.dao.impl.ClinicDaoImpl;
import com.MobyRx.java.entity.AddressEntity;
import com.MobyRx.java.entity.BaseEntity;
import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.entity.master.ClinicCategoryEntity;
import com.MobyRx.java.entity.master.ServiceEntity;
import com.MobyRx.java.service.wso.ClinicWSO;
import com.MobyRx.java.service.wso.DrugWSO;
import com.MobyRx.java.service.wso.WSOToEntityConversion;
import com.MobyRx.java.service.wso.ServiceWSO;
import com.MobyRx.java.service.wso.StatusWSO;
import com.MobyRx.java.util.ValidatorUtil;

@Repository("clinicBL")
@Transactional
public class ClinicBLImpl extends CommonBLImpl implements ClinicBL {

	@Autowired
    private ClinicDao clinicDao;
	
	
	private Logger logger = LoggerFactory.getLogger(ClinicBLImpl.class);
	
	public void save(ClinicWSO clinicWSO,StatusWSO statusWSO)  throws Exception{

		ValidatorUtil validatorUtil = new ValidatorUtil();
		validatorUtil.validateClinic(clinicWSO,statusWSO);
		if(statusWSO.getCode()==400)
		{
			return;
		}

		ClinicEntity clinicEntity = WSOToEntityConversion.ClinicWSOToClinicEntity(clinicWSO);
		clinicEntity.setId(null);
		clinicEntity.getAddress().setId(null);
		if(clinicWSO.getCategory().getId()!=null)
		{
			ClinicCategoryEntity clinicCategoryEntity= clinicDao.get(ClinicCategoryEntity.class,clinicWSO.getCategory().getId() );
			clinicEntity.setCategory(clinicCategoryEntity);
		}
		else
		{
			clinicEntity.setCategory(null);
		}

		if(clinicWSO.getServices()!=null && !clinicWSO.getServices().isEmpty())
		{
			List<ServiceEntity> serviceEntityList= new ArrayList<ServiceEntity>();
			for (ServiceWSO service : clinicWSO.getServices()) {
				ServiceEntity serviceEntity = clinicDao.get(ServiceEntity.class,service.getId() );
				serviceEntityList.add(serviceEntity);

			}
			clinicEntity.setServices(serviceEntityList);
		}


		clinicDao.save(clinicEntity);
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");

	}
	
	public void update(ClinicWSO clinicWSO,StatusWSO statusWSO)  throws Exception{
		
			ClinicEntity clinicEntity = clinicDao.get(ClinicEntity.class, clinicWSO.getId());
			AddressEntity addressEntity = clinicEntity.getAddress();
			ClinicCategoryEntity clinicCategoryEntity = clinicEntity.getCategory();
			List<ServiceEntity> ServiceEntityList= clinicEntity.getServices();
			
			for (ServiceWSO Service : clinicWSO.getServices()) {
				ServiceEntity serviceEntity = clinicDao.get(ServiceEntity.class,Service.getId() );
				ServiceEntityList.add(serviceEntity);

			}

			addressEntity.setBuildingNumber(clinicWSO.getAddress().getBuildingNumber());
			addressEntity.setCity(clinicWSO.getAddress().getCity());
			addressEntity.setCountry(clinicWSO.getAddress().getCountry());
			addressEntity.setCreatedAt(clinicWSO.getCreatedAt());
			addressEntity.setLandmark(clinicWSO.getAddress().getLandmark());
			addressEntity.setLatitude(clinicWSO.getAddress().getLatitude());
			addressEntity.setLongitude(clinicWSO.getAddress().getLongitude());
			addressEntity.setState(clinicWSO.getAddress().getState());
			addressEntity.setStreet(clinicWSO.getAddress().getStreet());
			addressEntity.setUpdatedAt(clinicWSO.getUpdatedAt());
			addressEntity.setZipCode(clinicWSO.getAddress().getZipCode());
			
			clinicCategoryEntity.setDescription(clinicWSO.getCategory().getDescription());
			clinicCategoryEntity.setName(clinicWSO.getCategory().getName());
			
			clinicEntity.setAddress(addressEntity);
			clinicEntity.setCategory(clinicCategoryEntity);
			clinicEntity.setCreatedAt(clinicWSO.getCreatedAt());
			clinicEntity.setEmail(clinicWSO.getEmail());
			clinicEntity.setLicenceNumber(clinicWSO.getLicenceNumber());
			clinicEntity.setName(clinicWSO.getName());
			clinicEntity.setPhoneNumber(clinicWSO.getPhoneNumber());
			clinicEntity.setRegistrationDate(clinicWSO.getRegistrationDate());
			clinicEntity.setServices(ServiceEntityList);
			clinicEntity.setUpdatedAt(clinicWSO.getUpdatedAt());
			clinicEntity.setUrl(clinicWSO.getUrl());
			clinicEntity.setVerified(clinicWSO.isVerified());
			clinicDao.update(clinicEntity);
			
			statusWSO.setCode(200);
			statusWSO.setMessage("Sucessful");
		
		
	}
	

	public ClinicEntity get(long id,StatusWSO statusWSO) throws Exception {
		ClinicEntity clinicEntity = clinicDao.get(ClinicEntity.class, id);
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");
		return clinicEntity;
	}
	
	public void delete(long id,StatusWSO statusWSO) throws Exception {

		ClinicEntity clinicEntity = clinicDao.get(ClinicEntity.class, id);

		clinicDao.delete(clinicEntity);
		
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");

		}

	public void save(DrugWSO drugWSO, StatusWSO statusWSO) throws Exception{
		DrugsEntity drugsEntity=WSOToEntityConversion.drugWSOToDrugsEntity(drugWSO);
		clinicDao.save(drugsEntity);
		
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");
		}

	public void delete(Long drugId , StatusWSO statusWSO) throws Exception {
		
		clinicDao.delete(DrugsEntity.class, drugId);
		
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");
		}
		
	}

	



