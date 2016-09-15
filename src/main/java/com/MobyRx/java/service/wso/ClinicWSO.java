package com.MobyRx.java.service.wso;

import com.MobyRx.java.entity.AddressEntity;
import com.MobyRx.java.entity.master.ClinicCategoryEntity;
import com.MobyRx.java.entity.master.ServiceEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/13/16
 * Time: 12:38 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "clinic")
public class ClinicWSO {

    private String name;
    private AddressWSO address;
    private String phoneNumber;
    private String email;
    private String licenceNumber;
    private Date registrationDate;
    private String url;
    private ClinicCategoryWSO category;
    private List<ServiceWSO> services;
    private boolean verified = false;
	public Date createdAt;
    public Date updatedAt;

    public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressWSO getAddress() {
        return address;
    }

    public void setAddress(AddressWSO address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ClinicCategoryWSO getCategory() {
        return category;
    }

    public void setCategory(ClinicCategoryWSO category) {
        this.category = category;
    }

    public List<ServiceWSO> getServices() {
        return services;
    }

    public void setServices(List<ServiceWSO> services) {
        this.services = services;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
