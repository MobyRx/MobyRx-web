package com.MobyRx.java.service.wso;


import com.MobyRx.java.service.wso.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;
import java.util.List;

@XmlRootElement(name = "ProfileWSO")
public class ProfileWSO extends BaseWSO{
	private String name;
	private AddressWSO address;
    private GenderWSO gender;
    private List<EmergencyContactWSO> emergencyContacts;
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserWSO getUser() {
		return user;
	}
	public void setUser(UserWSO user) {
		this.user = user;
	}
	public AddressWSO getAddress() {
		return address;
	}
	public void setAddress(AddressWSO address) {
		this.address = address;
	}
	public GenderWSO getGender() {
		return gender;
	}
	public void setGender(GenderWSO gender) {
		this.gender = gender;
	}
	public List<EmergencyContactWSO> getEmergencyContacts() {
		return emergencyContacts;
	}
	public void setEmergencyContacts(List<EmergencyContactWSO> emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}
	private UserWSO user;
    
}