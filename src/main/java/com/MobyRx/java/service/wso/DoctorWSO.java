package com.MobyRx.java.service.wso;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 12/12/16
 * Time: 11:34 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "doctor")
public class DoctorWSO {
    
    private Long id;
    private String name;
    private String mobile;
    private String email;
    private String gender;
    private AddressWSO address;
    private Map<String,String> emergencyContact;
    private String medRegNumber;
    private List<String> qualification;
    private List<String> achievements;
    private Map<String,String> certification;
    private Date practiceStartAt;
    private Set<AccountWSO> clinic;
    private boolean verified = false;
    private Set<String> specializations;
    private Map<String, String> availability;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public AddressWSO getAddress() {
        return address;
    }

    public void setAddress(AddressWSO address) {
        this.address = address;
    }

    public Map<String, String> getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(Map<String, String> emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getMedRegNumber() {
        return medRegNumber;
    }

    public void setMedRegNumber(String medRegNumber) {
        this.medRegNumber = medRegNumber;
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

    public Map<String, String> getCertification() {
        return certification;
    }

    public void setCertification(Map<String, String> certification) {
        this.certification = certification;
    }

    public Date getPracticeStartAt() {
        return practiceStartAt;
    }

    public void setPracticeStartAt(Date practiceStartAt) {
        this.practiceStartAt = practiceStartAt;
    }

    public Set<AccountWSO> getClinic() {
        return clinic;
    }

    public void setClinic(Set<AccountWSO> clinic) {
        this.clinic = clinic;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Set<String> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<String> specializations) {
        this.specializations = specializations;
    }
    
    public void addSpecialization(String name){
        if(null==getSpecializations())
            setSpecializations(new HashSet<String>());
        getSpecializations().add(name);
    }

    public Map<String, String> getAvailability() {
        return availability;
    }

    public void setAvailability(Map<String, String> availability) {
        this.availability = availability;
    }
    
    public void putAvailability(String day, String time){
        if(null == getAvailability())
            setAvailability(new HashMap<String, String>());
        getAvailability().put(day,time);
    }
}
