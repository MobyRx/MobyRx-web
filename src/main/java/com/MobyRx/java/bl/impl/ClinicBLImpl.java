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
import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.entity.master.ClinicCategoryEntity;
import com.MobyRx.java.entity.master.ServiceEntity;
import com.MobyRx.java.service.wso.ClinicWSO;
import com.MobyRx.java.service.wso.ServiceWSO;
import com.MobyRx.java.service.wso.StatusWSO;

@Repository("clinicBL")
@Transactional
public class ClinicBLImpl extends BaseBL implements ClinicBL {

	@Autowired
    private ClinicDao clinicDao;
	
	private Logger logger = LoggerFactory.getLogger(ClinicBLImpl.class);
	
	@Override
	public void save(ClinicWSO clinicWSO)  throws Exception{
		
			ClinicEntity clinicEntity = new ClinicEntity();
			AddressEntity addressEntity = new AddressEntity();
			ClinicCategoryEntity clinicCategoryEntity = new ClinicCategoryEntity();
			
			List<ServiceEntity> ServiceEntityList= new ArrayList<ServiceEntity>();
			for (ServiceWSO Service : clinicWSO.getServices()) {
				ServiceEntity serviceEntity = new ServiceEntity();
				serviceEntity.setDescription(Service.getDescription());
				serviceEntity.setName(Service.getName());
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
			clinicDao.save(clinicEntity);

		
		
	}

}
