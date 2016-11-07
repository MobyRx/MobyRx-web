package com.MobyRx.java.service.wso;


import java.util.Date;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PrescriptionWSO")
public class PrescriptionWSO extends BaseWSO{
    
    private Long prescriptionNumber;
    private ProfileWSO doctor;
    private ProfileWSO patient;
    private ClinicWSO clinic;
    private String instruction;
    private Date nextAppointment;
    private Set<PrescriptionItemWSO> prescriptionItems;



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


    public ClinicWSO getClinic() {
        return clinic;
    }

    public void setClinic(ClinicWSO clinic) {
        this.clinic = clinic;
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
}
