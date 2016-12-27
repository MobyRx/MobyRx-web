package com.MobyRx.java.bl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.MobyRx.java.dao.AccountDao;
import com.MobyRx.java.entity.common.AccountEntity;
import com.MobyRx.java.entity.common.AddressEntity;
import com.MobyRx.java.entity.doctor.DoctorProfileEntity;
import com.MobyRx.java.service.converter.DataMapper;
import com.MobyRx.java.service.wso.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.MobyRx.java.bl.AccountBL;
import com.MobyRx.java.dao.ClinicDao;
import com.MobyRx.java.entity.master.CategoryEntity;
import com.MobyRx.java.entity.master.ServiceEntity;
import com.MobyRx.java.util.ValidatorUtil;

@Repository("clinicBL")
@Transactional
public class AccountBLImpl extends BaseBL implements AccountBL {

    private Logger logger = LoggerFactory.getLogger(AccountBLImpl.class);

	@Autowired
	private ClinicDao clinicDao;

    @Autowired
    private AccountDao accountDao;

	public void save(ClinicWSO clinicWSO,StatusWSO statusWSO)  throws Exception{

		ValidatorUtil validatorUtil = new ValidatorUtil();
		validatorUtil.validate(clinicWSO,statusWSO);
		if(statusWSO.getCode()==400)
		{
			return;
		}

		AccountEntity accountEntity = WSOToEntityConversion.transform(clinicWSO);
		accountEntity.setId(null);
		accountEntity.getAddress().setId(null);
		if(clinicWSO.getCategory().getId()!=null)
		{
			CategoryEntity categoryEntity = clinicDao.get(CategoryEntity.class,clinicWSO.getCategory().getId() );
			accountEntity.setCategory(categoryEntity);
		}
		else
		{
			accountEntity.setCategory(null);
		}

		if(clinicWSO.getServices()!=null && !clinicWSO.getServices().isEmpty())
		{
			List<ServiceEntity> serviceEntityList= new ArrayList<ServiceEntity>();
			for (ServiceWSO service : clinicWSO.getServices()) {
				ServiceEntity serviceEntity = clinicDao.get(ServiceEntity.class,service.getId() );
				serviceEntityList.add(serviceEntity);

			}
			accountEntity.setServices(serviceEntityList);
		}


		clinicDao.save(accountEntity);
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");

	}

	public void update(ClinicWSO clinicWSO,StatusWSO statusWSO)  throws Exception{
		ValidatorUtil validatorUtil = new ValidatorUtil();
		validatorUtil.validate(clinicWSO,statusWSO);
		if(statusWSO.getCode()==400)
		{
			return;
		}
		AccountEntity accountEntity = clinicDao.get(AccountEntity.class, clinicWSO.getId());
		AddressEntity addressEntity = accountEntity.getAddress();
		CategoryEntity categoryEntity = accountEntity.getCategory();
		List<ServiceEntity> ServiceEntityList= accountEntity.getServices();

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
		categoryEntity.setDescription(clinicWSO.getCategory().getDescription());
		categoryEntity.setName(clinicWSO.getCategory().getName());
		accountEntity.setAddress(addressEntity);
		accountEntity.setCategory(categoryEntity);
		accountEntity.setCreatedAt(clinicWSO.getCreatedAt());
		accountEntity.setEmail(clinicWSO.getEmail());
		accountEntity.setLicenceNumber(clinicWSO.getLicenceNumber());
		accountEntity.setName(clinicWSO.getName());
		accountEntity.setPhoneNumber(clinicWSO.getPhoneNumber());
		accountEntity.setRegistrationDate(clinicWSO.getRegistrationDate());
		accountEntity.setServices(ServiceEntityList);
		accountEntity.setUpdatedAt(clinicWSO.getUpdatedAt());
		accountEntity.setUrl(clinicWSO.getUrl());
		accountEntity.setVerified(clinicWSO.isVerified());
		clinicDao.update(accountEntity);

		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");


	}


	public AccountEntity get(long id,StatusWSO statusWSO) throws Exception {
		AccountEntity accountEntity = clinicDao.get(AccountEntity.class, id);
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");
		return accountEntity;
	}

	public void delete(long id,StatusWSO statusWSO) throws Exception {
		clinicDao.delete(AccountEntity.class, id);
		statusWSO.setCode(200);
		statusWSO.setMessage("Sucessful");

	}

    @Override
    public List<DoctorWSO> getAccountDoctor(Long accountId, Map<String, String> filterParam) {
        List<DoctorProfileEntity> doctors = accountDao.getClinicDoctor(accountId,filterParam);
        return DataMapper.transformDoctors(doctors);
    }


}





