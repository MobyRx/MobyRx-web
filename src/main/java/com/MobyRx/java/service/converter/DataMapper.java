package com.MobyRx.java.service.converter;

import com.MobyRx.java.entity.common.AccountEntity;
import com.MobyRx.java.entity.common.AddressEntity;
import com.MobyRx.java.entity.doctor.DoctorProfileEntity;
import com.MobyRx.java.entity.master.ServiceEntity;
import com.MobyRx.java.entity.master.SpecializationEntity;
import com.MobyRx.java.service.wso.AccountWSO;
import com.MobyRx.java.service.wso.AddressWSO;
import com.MobyRx.java.service.wso.DoctorWSO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 12/12/16
 * Time: 11:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataMapper {
    
    public static List<AccountWSO> transform(List<AccountEntity> accounts){
        List<AccountWSO> accountWSOs = new ArrayList<AccountWSO>();
        for(AccountEntity account : accounts)
            accountWSOs.add(transform(account));
        return accountWSOs;
    }

    public static AccountWSO transform(AccountEntity account){
        AccountWSO accountWSO = new AccountWSO();
        accountWSO.setId(account.getId());
        accountWSO.setName(account.getName());
        accountWSO.setEmail(account.getEmail());
        accountWSO.setCategory(account.getCategory().getName());
        accountWSO.setLicenceNumber(account.getLicenceNumber());
        accountWSO.setPhoneNumber(account.getPhoneNumber());
        accountWSO.setAccountType(account.getAccountType().name());
        accountWSO.setUrl(account.getUrl());
        accountWSO.setVerified(account.isVerified());
        accountWSO.setRegistrationDate(account.getRegistrationDate());
        accountWSO.setAddress(transform(account.getAddress()));
        for(ServiceEntity service : account.getServices())
            accountWSO.addService(service.getName());
        return accountWSO;
    }
    
    
    public static AddressWSO transform(AddressEntity address){
        AddressWSO addressWSO = new AddressWSO();
        addressWSO.setStreet(address.getStreet());
        addressWSO.setBuildingNumber(address.getBuildingNumber());
        addressWSO.setCity(address.getCity());
        addressWSO.setCountry(address.getCountry());
        addressWSO.setZipCode(address.getZipCode());
        addressWSO.setLandmark(address.getLandmark());
        addressWSO.setLatitude(address.getLatitude());
        addressWSO.setLongitude(address.getLongitude());
        return addressWSO;
    }


    public static List<DoctorWSO> transformDoctors(List<DoctorProfileEntity> doctors){
        List<DoctorWSO> doctorWSOs = new ArrayList<DoctorWSO>();
        for(DoctorProfileEntity doctor : doctors)
            doctorWSOs.add(transform(doctor));
        return doctorWSOs;
    }
    
    public static DoctorWSO transform(DoctorProfileEntity doctor){
        DoctorWSO doctorWSO = new DoctorWSO();
        doctorWSO.setId(doctor.getId());
        //doctorWSO.setAddress(transform(doctor.getAddress()));
        doctorWSO.setGender(doctor.getGender().toString());
        doctorWSO.setName(doctor.getName());
        doctorWSO.setMobile(doctor.getUser().getMobile());
        doctorWSO.setEmail(doctor.getUser().getEmail());
        doctorWSO.setAchievements(doctor.getAchievements());
        doctorWSO.setQualification(doctor.getQualification());
        doctorWSO.setVerified(doctor.isVerified());
        for(SpecializationEntity specialization  : doctor.getSpecializations())
            doctorWSO.addSpecialization(specialization.getName());
        doctorWSO.setPracticeStartAt(doctor.getPracticeStartAt());
        doctorWSO.setMedRegNumber(doctor.getMedRegNumber());
        return doctorWSO;
    }





}
