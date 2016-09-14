package com.MobyRx.java.entity;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/12/16
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "otp")
public class OTPEntity extends BaseEntity{
    
    
    private UserEntity user;
    private String otp;
    private String sentTo;
    private String sendFrom;
    private boolean  verified;
    private String verifiedFrom;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Column(name = "otp", nullable = false, updatable =false)
    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public String getSendFrom() {
        return sendFrom;
    }

    public void setSendFrom(String sendFrom) {
        this.sendFrom = sendFrom;
    }

    @Column(name = "is_verified")
    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getVerifiedFrom() {
        return verifiedFrom;
    }

    public void setVerifiedFrom(String verifiedFrom) {
        this.verifiedFrom = verifiedFrom;
    }
}
