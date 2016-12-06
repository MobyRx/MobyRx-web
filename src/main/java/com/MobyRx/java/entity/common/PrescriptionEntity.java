package com.MobyRx.java.entity.common;

import com.MobyRx.java.entity.type.PrescriptionStatus;
import com.MobyRx.java.entity.type.PrescriptionType;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/6/16
 * Time: 9:48 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "prescription")
@XmlRootElement(name = "prescription")
public class PrescriptionEntity extends BaseEntity {
    
    private Long prescriptionNumber;
    private ProfileEntity doctor;
    private ProfileEntity patient;
    private AccountEntity account;
    private String instruction;
    private Date nextAppointment;
    private Set<PrescriptionItemEntity> prescriptionItems;
    private String filePaths;
    private PrescriptionType prescriptionType;
    private PrescriptionStatus status;


    @Column(name = "prescription_number")
    public Long getPrescriptionNumber() {
        return prescriptionNumber;
    }

    public void setPrescriptionNumber(Long prescriptionNumber) {
        this.prescriptionNumber = prescriptionNumber;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = true)
    public ProfileEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(ProfileEntity doctor) {
        this.doctor = doctor;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    public ProfileEntity getPatient() {
        return patient;
    }

    public void setPatient(ProfileEntity patient) {
        this.patient = patient;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", nullable = true)
    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Column(name = "next_appointment")
    public Date getNextAppointment() {
        return nextAppointment;
    }

    public void setNextAppointment(Date nextAppointment) {
        this.nextAppointment = nextAppointment;
    }

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    public Set<PrescriptionItemEntity> getPrescriptionItems() {
        return prescriptionItems;
    }

    public void setPrescriptionItems(Set<PrescriptionItemEntity> prescriptionItems) {
        this.prescriptionItems = prescriptionItems;
    }

    @Column(name = "prescription_path")
    public String getFilePaths() {
        return filePaths;
    }

    public void setFilePaths(String filePaths) {
        this.filePaths = filePaths;
    }

    @Column(name = "prescription_type")
    @Enumerated(EnumType.STRING)
    public PrescriptionType getPrescriptionType() {
        return prescriptionType;
    }

    public void setPrescriptionType(PrescriptionType prescriptionType) {
        this.prescriptionType = prescriptionType;
    }

    @Column(name = "prescription_status")
    @Enumerated(EnumType.STRING)
    public PrescriptionStatus getStatus() {
        return status;
    }

    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }
}
