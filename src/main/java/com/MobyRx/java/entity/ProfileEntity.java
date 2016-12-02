package com.MobyRx.java.entity;


import com.MobyRx.java.entity.converter.EmergencyContactConverter;
import com.MobyRx.java.entity.type.Gender;
import com.MobyRx.java.entity.type.Status;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/7/16
 * Time: 11:05 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "profile")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name="classname")
public class ProfileEntity extends BaseEntity{

    private String name;
    private UserEntity user;
    private AddressEntity address;
    private Gender gender;
    private String emergencyContact;
    private Status status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = true)
    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    @Enumerated
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(name = "emergency_contact")
    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }


    @Transient
    public List<EmergencyContact> getEmergencyContacts() {
        return EmergencyContactConverter.convertToEntityAttribute(this.emergencyContact);
    }

    public void setEmergencyContacts(List<EmergencyContact> emergencyContacts) {
        this.emergencyContact = EmergencyContactConverter.convertToDatabaseColumn(emergencyContacts);
    }

    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
