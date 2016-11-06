package com.MobyRx.java.entity;


import com.MobyRx.java.entity.converter.EmergencyContactConverter;
import com.MobyRx.java.entity.converter.StringArrayToStringConverter;
import com.MobyRx.java.entity.type.Gender;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

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
@XmlRootElement(name = "profile")
public class ProfileEntity extends BaseEntity{

    private String name;
    private UserEntity user;
    private AddressEntity address;
    private Gender gender;
    private List<EmergencyContact> emergencyContacts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
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

    @Transient
    @Column(name = "emergency_contact")
    @Convert(converter = EmergencyContactConverter.class)
    public List<EmergencyContact> getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(List<EmergencyContact> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }
}
