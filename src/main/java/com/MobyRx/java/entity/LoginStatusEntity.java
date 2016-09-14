package com.MobyRx.java.entity;

import com.MobyRx.java.entity.type.LoginStatus;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/11/16
 * Time: 1:59 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "login_status")
public class LoginStatusEntity extends BaseEntity{
    
    
    private UserEntity user;
    private String token;
    private String deviceId;
    private LoginStatus loginStatus = LoginStatus.LOGIN;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Enumerated
    public LoginStatus getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(LoginStatus loginStatus) {
        this.loginStatus = loginStatus;
    }
}
