package com.MobyRx.java.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/6/16
 * Time: 5:34 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "address")
@XmlRootElement(name = "address")
public class AddressEntity extends BaseEntity{
    
    public String buildingNumber;
    public String  street;
    public String  landmark;
    public String  city;
    public String  state;
    public String  country;
    public String  zipCode;
    public Long latitude;
    public Long longitude;

    @Column(name = "building_number")
    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }
}
