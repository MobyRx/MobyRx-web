package com.MobyRx.java.entity.common;


import com.MobyRx.java.entity.common.AddressEntity;
import com.MobyRx.java.entity.common.BaseEntity;
import com.MobyRx.java.entity.master.CategoryEntity;
import com.MobyRx.java.entity.master.ServiceEntity;
import com.MobyRx.java.entity.type.AccountFunction;
import com.MobyRx.java.entity.type.AccountType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/6/16
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "account")
public class AccountEntity extends BaseEntity {
    
    private String name;
    private AddressEntity address;
    private String phoneNumber;
    private String email;
    private String licenceNumber;
    private Date registrationDate;
    private String url;
    private CategoryEntity category;
    private List<ServiceEntity> services;
    private boolean verified = false;
    private AccountType accountType = AccountType.TRIAL;
    private AccountFunction accountFunction;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
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

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

   
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "clinic_service", joinColumns = @JoinColumn(name = "clinic_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    public List<ServiceEntity> getServices() {
        return services;
    }

    public void setServices(List<ServiceEntity> services) {
        this.services = services;
    }

    @Column(name = "is_verified")
    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }


    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Column(name = "account_function", nullable = false)
    @Enumerated(EnumType.STRING)
    public AccountFunction getAccountFunction() {
        return accountFunction;
    }

    public void setAccountFunction(AccountFunction accountFunction) {
        this.accountFunction = accountFunction;
    }
}