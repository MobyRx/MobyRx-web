package com.MobyRx.java.service.wso;

import com.MobyRx.java.entity.converter.StringArrayToStringConverter;
import com.MobyRx.java.entity.master.SpecializationEntity;
import com.MobyRx.java.entity.type.Gender;
import com.MobyRx.java.entity.converter.*;
import com.MobyRx.java.service.wso.ClinicWSO;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;
import java.util.List;
import java.util.Set;

@XmlRootElement(name = "doctor")
public class DoctorProfileWSO extends ProfileWSO{

    private String medRegNumber;
    private Set<SpecializationWSO> specializations;
    private List<String> qualification;
    private List<String> achievements;
    private List<String> certification;
    private List<String> certificateNumber;
    private Date practiceStartAt;
    private Set<ClinicWSO> clinic;
    private boolean verified = false;


    public String getMedRegNumber() {
        return medRegNumber;
    }

    public void setMedRegNumber(String medRegNumber) {
        this.medRegNumber = medRegNumber;
    }



    public Set<SpecializationWSO> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<SpecializationWSO> specializations) {
        this.specializations = specializations;
    }


    public List<String> getQualification() {
        return qualification;
    }

    public void setQualification(List<String> qualification) {
        this.qualification = qualification;
    }


    public List<String> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<String> achievements) {
        this.achievements = achievements;
    }

    public List<String> getCertification() {
        return certification;
    }

    public void setCertification(List<String> certification) {
        this.certification = certification;
    }


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


    public Set<ClinicWSO> getClinic() {
        return clinic;
    }

    public void setClinic(Set<ClinicWSO> clinic) {
        this.clinic = clinic;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
