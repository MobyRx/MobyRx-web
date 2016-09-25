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
import com.MobyRx.java.entity.master.ClinicCategoryEntity;
import com.MobyRx.java.entity.master.ServiceEntity;
import com.MobyRx.java.service.wso.ClinicWSO;
import com.MobyRx.java.service.wso.DataMapper;
import com.MobyRx.java.service.wso.ServiceWSO;
import com.MobyRx.java.service.wso.StatusWSO;

@Repository("clinicBL")
@Transactional
public class ClinicBLImpl extends CommonBLImpl implements ClinicBL {

	@Autowired
    private ClinicDao clinicDao;
	
	@Autowired
    private ClinicWSO clinic;
	
	private Logger logger = LoggerFactory.getLogger(ClinicBLImpl.class);
	
	public void save(ClinicWSO clinicWSO)  throws Exception{
		
			ClinicEntity clinicEntity = new ClinicEntity();
			AddressEntity addressEntity = DataMapper.addressWSOToAddressEntity(clinicWSO.getAddress());
			addressEntity.setId(null);
			ClinicCategoryEntity clinicCategoryEntity = new ClinicCategoryEntity();
			
			List<ServiceEntity> ServiceEntityList= new ArrayList<ServiceEntity>();
			for (ServiceWSO Service : clinicWSO.getServices()) {
				ServiceEntity serviceEntity = new ServiceEntity();
				serviceEntity.setDescription(Service.getDescription());
				serviceEntity.setName(Service.getName());
				ServiceEntityList.add(serviceEntity);

			}

			
			
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
	
	public void modify(ClinicWSO clinicWSO)  throws Exception{
		
			ClinicEntity clinicEntity = clinicDao.get(ClinicEntity.class, clinicWSO.getId());
			AddressEntity addressEntity = clinicEntity.getAddress();
			ClinicCategoryEntity clinicCategoryEntity = clinicEntity.getCategory();
			List<ServiceEntity> ServiceEntityList= clinicEntity.getServices();
			
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
			clinicDao.update(clinicEntity);

		
		
	}
	

	public ClinicWSO get(long id) throws Exception {
		ClinicEntity clinicEntity = clinicDao.get(ClinicEntity.class, id);
		
		clinic.getAddress().setBuildingNumber(clinicEntity.getAddress().getBuildingNumber());
		clinic.getAddress().setCity(clinicEntity.getAddress().getCity());
		clinic.getAddress().setCountry(clinicEntity.getAddress().getCountry());
		clinic.getAddress().setCreatedAt(clinicEntity.getAddress().getCreatedAt());
		clinic.getAddress().setId(clinicEntity.getAddress().getId());
		clinic.getAddress().setLandmark(clinicEntity.getAddress().getLandmark());
		clinic.getAddress().setLatitude(clinicEntity.getAddress().getLatitude());
		clinic.getAddress().setLongitude(clinicEntity.getAddress().getLongitude());
		clinic.getAddress().setState(clinicEntity.getAddress().getState());
		clinic.getAddress().setStreet(clinicEntity.getAddress().getStreet());
		clinic.getAddress().setUpdatedAt(clinicEntity.getAddress().getUpdatedAt());
		clinic.getAddress().setZipCode(clinicEntity.getAddress().getZipCode());

		
		List<ServiceWSO> ServiceEntityList= new ArrayList<ServiceWSO>();

		for (ServiceEntity Service : clinicEntity.getServices()) {
			ServiceWSO serviceEntity = new ServiceWSO();
			serviceEntity.setDescription(Service.getDescription());
			serviceEntity.setName(Service.getName());
			ServiceEntityList.add(serviceEntity);

		}

		clinic.setServices(ServiceEntityList);
		
		clinic.getCategory().setDescription(clinicEntity.getCategory().getDescription());
		clinic.getCategory().setId(clinicEntity.getCategory().getId());
		clinic.getCategory().setName(clinicEntity.getCategory().getName());

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
		

		logger.info("clinicWSO="+clinic.toString());
		return clinic;
	}
	
	public void delete(long id) throws Exception {

		ClinicEntity clinicEntity = clinicDao.get(ClinicEntity.class, id);

		clinicDao.delete(clinicEntity);

		}

	


}
