package com.MobyRx.java.service.converter;

import com.MobyRx.java.entity.common.AccountEntity;
import com.MobyRx.java.entity.common.AddressEntity;
import com.MobyRx.java.entity.common.PrescriptionEntity;
import com.MobyRx.java.entity.doctor.DoctorProfileEntity;
import com.MobyRx.java.entity.master.ServiceEntity;
import com.MobyRx.java.entity.master.SpecializationEntity;
import com.MobyRx.java.entity.patient.AppointmentEntity;
import com.MobyRx.java.entity.type.PrescriptionStatus;
import com.MobyRx.java.entity.type.PrescriptionType;
import com.MobyRx.java.service.wso.*;

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

    public static List<PrescriptionWSO> transformPrescription(List<PrescriptionEntity> prescriptionEntitys) {
		List<PrescriptionWSO> prescriptionWSOList = new ArrayList<PrescriptionWSO>();
		
		for (int i=0;i<prescriptionEntitys.size();i++) {
			PrescriptionWSO prescriptionWSO = new PrescriptionWSO();
			//prescriptionWSO.setPharmacy(transform(prescriptionEntitys.get(i).getPharmacy()));
			prescriptionWSO.setCreatedAt(prescriptionEntitys.get(i).getCreatedAt());
			if(prescriptionEntitys.get(i).getDoctor()!=null)
			//prescriptionWSO.setDoctor(com.MobyRx.java.service.wso.DataMapper.transform(prescriptionEntitys.get(i).getDoctor()));
			prescriptionWSO.setId(prescriptionEntitys.get(i).getId());
			prescriptionWSO.setInstruction(prescriptionEntitys.get(i).getInstruction());
			prescriptionWSO.setNextAppointment(prescriptionEntitys.get(i).getNextAppointment());
			if(prescriptionEntitys.get(i).getPatient()!=null)
			//prescriptionWSO.setPatient(com.MobyRx.java.service.wso.DataMapper.transform(prescriptionEntitys.get(i).getPatient()));
			
			prescriptionWSO.setPrescriptionNumber(prescriptionEntitys.get(i).getPrescriptionNumber());
			prescriptionWSO.setUpdatedAt(prescriptionEntitys.get(i).getUpdatedAt());
			prescriptionWSO.setFilePaths(prescriptionEntitys.get(i).getFilePaths());
			prescriptionWSO.setStatus(PrescriptionStatusWSO.valueOf(prescriptionEntitys.get(i).getStatus().name()));
			prescriptionWSO.setPrescriptionType(PrescriptionTypeWSO.valueOf(prescriptionEntitys.get(i).getPrescriptionType().name()));
			
			/*Set<PrescriptionItemWSO> prescriptionItemWSOSet =new HashSet<PrescriptionItemWSO>();
			Set<PrescriptionItemEntity> prescriptionItemEntitySet =prescriptionEntitys.get(i).getPrescriptionItems();
			for (Iterator<PrescriptionItemEntity> it = prescriptionItemEntitySet.iterator(); it.hasNext(); ) {
				PrescriptionItemEntity prescriptionItemEntity= it.next();
				PrescriptionItemWSO prescriptionItemWSO = new PrescriptionItemWSO();
				prescriptionItemWSO.setBeforeFood(prescriptionItemEntity.isBeforeFood());
				prescriptionItemWSO.setCreatedAt(prescriptionItemEntity.getCreatedAt());
				prescriptionItemWSO.setDoseType(DoseWSO.valueOf(prescriptionItemEntity.getDoseType().name()));
				prescriptionItemWSO.setDrugName(prescriptionItemEntity.getDrugName());
				prescriptionItemWSO.setDrugs(com.MobyRx.java.service.wso.DataMapper.transform(prescriptionItemEntity.getDrugs()));
				prescriptionItemWSO.setDuration(prescriptionItemEntity.getDuration());
				prescriptionItemWSO.setDurationType(DurationWSO.valueOf(prescriptionItemEntity.getDurationType().name()));
				prescriptionItemWSO.setId(prescriptionItemEntity.getId());
				prescriptionItemWSO.setInstruction(prescriptionItemEntity.getInstruction());
				prescriptionItemWSO.setPrescription(transform(prescriptionItemEntity.getPrescription()));
				prescriptionItemWSO.setQuantity(prescriptionItemEntity.getQuantity());
				prescriptionItemWSO.setUpdatedAt(prescriptionItemEntity.getCreatedAt());
				
				prescriptionItemWSOSet.add(prescriptionItemWSO);
			
			}
			
			prescriptionWSO.setPrescriptionItems(prescriptionItemWSOSet);*/
			
			prescriptionWSOList.add(prescriptionWSO);
		}
		
		return prescriptionWSOList;
		
		
	}

	private static PrescriptionWSO transform(PrescriptionEntity prescription) {
		PrescriptionWSO prescriptionWSO = new PrescriptionWSO();
		prescriptionWSO.setPharmacy(transform(prescription.getPharmacy()));
		prescriptionWSO.setCreatedAt(prescription.getCreatedAt());
		prescriptionWSO.setDoctor(com.MobyRx.java.service.wso.DataMapper.transform(prescription.getDoctor()));
		prescriptionWSO.setId(prescription.getId());
		prescriptionWSO.setInstruction(prescription.getInstruction());
		prescriptionWSO.setNextAppointment(prescription.getNextAppointment());
		prescriptionWSO.setPatient(com.MobyRx.java.service.wso.DataMapper.transform(prescription.getPatient()));
		prescriptionWSO.setPrescriptionItems(null);
		prescriptionWSO.setPrescriptionNumber(prescription.getPrescriptionNumber());
		prescriptionWSO.setUpdatedAt(prescription.getUpdatedAt());
		prescriptionWSO.setFilePaths(prescription.getFilePaths());
		prescriptionWSO.setStatus(PrescriptionStatusWSO.valueOf(prescription.getStatus().name()));
		prescriptionWSO.setPrescriptionType(PrescriptionTypeWSO.valueOf(prescription.getPrescriptionType().name()));
	
		return prescriptionWSO;
	}

	public static PrescriptionStatus transform(PrescriptionStatusWSO prescriptionStatusWSO)
	{


		if( prescriptionStatusWSO.name().equals(PrescriptionStatus.ACCEPTED.name()) )
			return PrescriptionStatus.ACCEPTED;
		else if( prescriptionStatusWSO.name().equals(PrescriptionStatus.DELIVER.name()) )
			return PrescriptionStatus.DELIVER;
		else if( prescriptionStatusWSO.name().equals(PrescriptionStatus.GENERATED.name()) )
			return PrescriptionStatus.GENERATED;
		else if( prescriptionStatusWSO.name().equals(PrescriptionStatus.IN_TRANSIT.name()) )
			return PrescriptionStatus.IN_TRANSIT;
		return null;


	}
	
	public static PrescriptionType transform(PrescriptionTypeWSO prescriptionTypeWSO)
	{

		if( prescriptionTypeWSO.name().equals(PrescriptionType.E_PRESCRIPTION.name()) )
			return PrescriptionType.E_PRESCRIPTION;
		else if( prescriptionTypeWSO.name().equals(PrescriptionType.PAPER_PRESCRIPTION.name()) )
			return PrescriptionType.PAPER_PRESCRIPTION;
		
		return null;


	}


    public static List<AppointmentWSO> transformAppointments(List<AppointmentEntity> appointments){
        List<AppointmentWSO> appointmentWSOs = new ArrayList<AppointmentWSO>();
        for(AppointmentEntity appointment : appointments){
            appointmentWSOs.add(transform(appointment));
        }
        return appointmentWSOs;
    }

    public static AppointmentWSO transform(AppointmentEntity appointment){
        AppointmentWSO appointmentWSO = new AppointmentWSO();
        appointmentWSO.setId(appointment.getId());
        appointmentWSO.setDoctor(new EntityReference(appointment.getDoctor().getId(),appointment.getDoctor().getName()));
        appointmentWSO.setPatient(new EntityReference(appointment.getPatient().getId(),appointment.getPatient().getName()));
        appointmentWSO.setClinic(new EntityReference(appointment.getClinic().getId(),appointment.getClinic().getName()));
        appointmentWSO.setAppointmentOn(appointment.getAppointmentOn());
        appointmentWSO.setTime(appointment.getTime());
        appointmentWSO.setToken(appointment.getToken());
        appointmentWSO.setStatus(appointment.getStatus().name());
        return appointmentWSO;
    }
}
