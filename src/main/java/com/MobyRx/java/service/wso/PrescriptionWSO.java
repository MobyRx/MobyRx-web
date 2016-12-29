package com.MobyRx.java.service.wso;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import com.MobyRx.java.service.wso.*;


@XmlRootElement(name = "PrescriptionWSO")
public class PrescriptionWSO extends BaseWSO{
    
    private Long prescriptionNumber;
    private ProfileWSO doctor;
    private ProfileWSO patient;
    private AccountWSO pharmacy;
    private String instruction;
    private Date nextAppointment;
    private Set<PrescriptionItemWSO> prescriptionItems;
    private String filePaths;
    private PrescriptionTypeWSO prescriptionTypeWSO;
    private PrescriptionStatusWSO status;
    private String imageAsString;


    
    public Long getPrescriptionNumber() {
        return prescriptionNumber;
    }

    public void setPrescriptionNumber(Long prescriptionNumber) {
        this.prescriptionNumber = prescriptionNumber;
    }

   
    public ProfileWSO getDoctor() {
        return doctor;
    }

    public void setDoctor(ProfileWSO doctor) {
        this.doctor = doctor;
    }

    public ProfileWSO getPatient() {
        return patient;
    }

    public void setPatient(ProfileWSO patient) {
        this.patient = patient;
    }

  
    public AccountWSO getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(AccountWSO pharmacy) {
        this.pharmacy = pharmacy;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

  
    public Date getNextAppointment() {
        return nextAppointment;
    }

    public void setNextAppointment(Date nextAppointment) {
        this.nextAppointment = nextAppointment;
    }


    public Set<PrescriptionItemWSO> getPrescriptionItems() {
        return prescriptionItems;
    }

    public void setPrescriptionItems(Set<PrescriptionItemWSO> prescriptionItems) {
        this.prescriptionItems = prescriptionItems;
    }


    public String getFilePaths() {
        return filePaths;
    }

    public void setFilePaths(String filePaths) {
        this.filePaths = filePaths;
    }


    public PrescriptionTypeWSO getPrescriptionType() {
        return prescriptionTypeWSO;
    }

    public void setPrescriptionType(PrescriptionTypeWSO prescriptionTypeWSO) {
        this.prescriptionTypeWSO = prescriptionTypeWSO;
    }


    public PrescriptionStatusWSO getStatus() {
        return status;
    }

    public void setStatus(PrescriptionStatusWSO status) {
        this.status = status;
    }

	public String getImageAsString() {
		return imageAsString;
	}

	public void setImageAsString(String imageAsString) {
		this.imageAsString = imageAsString;
	}
}
