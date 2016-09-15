package com.MobyRx.java.bl.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.MobyRx.java.bl.ClinicBL;
import com.MobyRx.java.dao.impl.ClinicDaoImpl;
import com.MobyRx.java.entity.AddressEntity;
import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.entity.master.ServiceEntity;
import com.MobyRx.java.service.wso.ClinicWSO;
import com.MobyRx.java.service.wso.ServiceWSO;
import com.MobyRx.java.service.wso.StatusWSO;

public class ClinicBLImpl extends BaseBL implements ClinicBL {

	private Logger logger = LoggerFactory.getLogger(ClinicBLImpl.class);
	
	public StatusWSO addClinic(ClinicWSO clinicWSO) {

		StatusWSO statusWSO = new StatusWSO();
		try
		{
			ClinicEntity clinicEntity = new ClinicEntity();
			AddressEntity addressEntity = new AddressEntity();
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
			addressEntity.setCreatedAt(null);
			//addressEntity.setId(id);
			addressEntity.setLandmark(clinicWSO.getAddress().getLandmark());
			addressEntity.setLatitude(clinicWSO.getAddress().getLatitude());
			addressEntity.setLongitude(clinicWSO.getAddress().getLongitude());
			addressEntity.setState(clinicWSO.getAddress().getState());
			addressEntity.setStreet(clinicWSO.getAddress().getStreet());
			addressEntity.setUpdatedAt(null);
			addressEntity.setZipCode(clinicWSO.getAddress().getZipCode());
			//session.save(addressEntity);

			clinicEntity.setAddress(addressEntity);
			clinicEntity.setCategory(null);
			clinicEntity.setCreatedAt(null);
			clinicEntity.setEmail(clinicWSO.getEmail());
			//clinicEntity.setId(id);
			clinicEntity.setLicenceNumber(clinicWSO.getLicenceNumber());
			clinicEntity.setName(clinicWSO.getName());
			clinicEntity.setPhoneNumber(clinicWSO.getPhoneNumber());
			clinicEntity.setRegistrationDate(clinicWSO.getRegistrationDate());
			clinicEntity.setServices(ServiceEntityList);
			clinicEntity.setUpdatedAt(null);
			clinicEntity.setUrl(clinicWSO.getUrl());
			clinicEntity.setVerified(clinicWSO.isVerified());
			statusWSO = new ClinicDaoImpl().saveClinic(clinicEntity);

		}
		catch(Exception Ex)
		{
			statusWSO.setCode(404);
			logger.info("ClinicBLImpl error="+Ex.getMessage() + "error while creating clinic");
			statusWSO.setMessage(Ex.getMessage() + "error while creating clinic");
			return statusWSO;
		}
		
		return statusWSO;

	}

}
