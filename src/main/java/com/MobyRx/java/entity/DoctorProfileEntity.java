package com.MobyRx.java.entity;

import com.MobyRx.java.entity.converter.StringArrayToStringConverter;
import com.MobyRx.java.entity.master.SpecializationEntity;
import com.MobyRx.java.entity.type.Gender;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/7/16
 * Time: 11:04 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@XmlRootElement(name = "doctor")
public class DoctorProfileEntity extends ProfileEntity{

    private String medRegNumber;
    private Set<SpecializationEntity> specializations;
    private List<String> qualification;
    private List<String> achievements;
    private List<String> certification;
    private List<String> certificateNumber;
    private Date practiceStartAt;
    private Set<ClinicEntity> clinic;
    private boolean verified = false;

    @Column(name = "med_reg_number")
    public String getMedRegNumber() {
        return medRegNumber;
    }

    public void setMedRegNumber(String medRegNumber) {
        this.medRegNumber = medRegNumber;
    }

    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "doctor_specialization_p", joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id"))
    public Set<SpecializationEntity> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<SpecializationEntity> specializations) {
        this.specializations = specializations;
    }

    @Column(name = "qualification")
    @Convert(converter = StringArrayToStringConverter.class)
    public List<String> getQualification() {
        return qualification;
    }

    public void setQualification(List<String> qualification) {
        this.qualification = qualification;
    }

    @Column(name = "achievements")
    @Convert(converter = StringArrayToStringConverter.class)
    public List<String> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<String> achievements) {
        this.achievements = achievements;
    }

    @Column(name = "certificate_name")
    @Convert(converter = StringArrayToStringConverter.class)
    public List<String> getCertification() {
        return certification;
    }

    public void setCertification(List<String> certification) {
        this.certification = certification;
    }

    @Column(name = "certificate_number")
    @Convert(converter = StringArrayToStringConverter.class)
    public List<String> getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(List<String> certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public Date getPracticeStartAt() {
        return practiceStartAt;
    }

    public void setPracticeStartAt(Date practiceStartAt) {
        this.practiceStartAt = practiceStartAt;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "doctor_clinic_P", joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "clinic_id"))
    public Set<ClinicEntity> getClinic() {
        return clinic;
    }

    public void setClinic(Set<ClinicEntity> clinic) {
        this.clinic = clinic;
    }

    @Column(name = "is_verified")
    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
