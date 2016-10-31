package com.MobyRx.java.service.wso;

import java.util.ArrayList;
import java.util.List;


import com.MobyRx.java.entity.DoctorProfileEntity;
import com.MobyRx.java.entity.PatientProfileEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.entity.master.ServiceEntity;

public class DataMapper {

    private static Logger logger = LoggerFactory.getLogger(DataMapper.class);

    public static PatientProfileWSO transform(PatientProfileEntity patientProfile) {
        PatientProfileWSO patientProfileWSO = new PatientProfileWSO();
        patientProfileWSO.setId(patientProfile.getId());
        patientProfileWSO.setAge(patientProfile.getAge());
        patientProfileWSO.setBloodGroup(BloodGroupWSO.valueOf(patientProfile.getBloodGroup().name()));
        patientProfileWSO.setDob(patientProfile.getDob());
        patientProfileWSO.setRelationship(RelationshipWSO.valueOf(patientProfile.getRelationship().name()));
        patientProfileWSO.setName(patientProfile.getName());
        patientProfileWSO.setGender(GenderWSO.valueOf(patientProfile.getGender().name()));
        return patientProfileWSO;
    }

    public static List<PatientProfileWSO> transformPatients(List<PatientProfileEntity> patientProfiles) {
        List<PatientProfileWSO> patientProfileWSOs = new ArrayList<PatientProfileWSO>();
        for (PatientProfileEntity patientProfileEntity : patientProfiles)
            patientProfileWSOs.add(transform(patientProfileEntity));
        return patientProfileWSOs;
    }


    public static ClinicWSO clinicEntityToClinicWSo(ClinicEntity clinicEntity) {
        ClinicWSO clinic = new ClinicWSO();
        AddressWSO addressWSO = new AddressWSO();
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
        List<ServiceWSO> serviceEntityList = new ArrayList<ServiceWSO>();
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

    public static List<DoctorProfileWSO> transformDoctors(List<DoctorProfileEntity> doctors){
        List<DoctorProfileWSO> doctorProfileWSOs = new ArrayList<DoctorProfileWSO>();
        for(DoctorProfileEntity doctorProfile : doctors){
            doctorProfileWSOs.add(transform(doctorProfile));
        }
        return doctorProfileWSOs;
    }

    public static DoctorProfileWSO transform(DoctorProfileEntity doctorProfile){
        DoctorProfileWSO doctorProfileWSO = new DoctorProfileWSO();
        doctorProfileWSO.setId(doctorProfile.getId());
        return doctorProfileWSO;
    }

}
