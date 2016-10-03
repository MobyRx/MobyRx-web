package com.MobyRx.java.service.wso;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.entity.master.ServiceEntity;
import com.MobyRx.java.service.ClinicService;

public class EntityToWSOConversion {
	private  static Logger logger = LoggerFactory.getLogger(EntityToWSOConversion.class);

	public static ClinicWSO clinicEntityToClinicWSo(ClinicEntity clinicEntity) {
		ClinicWSO clinic = new ClinicWSO();

		AddressWSO 	addressWSO=  new AddressWSO();
		addressWSO.setBuildingNumber(clinicEntity.getAddress().getBuildingNumber());
		addressWSO.setCity(clinicEntity.getAddress().getCity());
		addressWSO.setCountry(clinicEntity.getAddress().getCountry());
		addressWSO.setCreatedAt(clinicEntity.getAddress().getCreatedAt());
		addressWSO.setId(clinicEntity.getAddress().getId());
		addressWSO.setLandmark(clinicEntity.getAddress().getLandmark());
		addressWSO.setLatitude(clinicEntity.getAddress().getLatitude());
		addressWSO.setLongitude(clinicEntity.getAddress().getLongitude());
		addressWSO.setState(clinicEntity.getAddress().getState());
		addressWSO.setStreet(clinicEntity.getAddress().getStreet());
		addressWSO.setUpdatedAt(clinicEntity.getAddress().getUpdatedAt());
		addressWSO.setZipCode(clinicEntity.getAddress().getZipCode());

		clinic.setAddress(addressWSO);

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

}
