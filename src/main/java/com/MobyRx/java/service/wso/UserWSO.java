package com.MobyRx.java.service.wso;

import java.util.Set;


/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/6/16
 * Time: 1:10 PM
 * To change this template use File | Settings | File Templates.
 */

public class UserWSO extends BaseWSO{


    private String username;
    private String mobile;
    private String email;
    private String password;
    private boolean emailVerified;
    private boolean mobileVerified;
    private Set<RoleWSO> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }


    public boolean isMobileVerified() {
        return mobileVerified;
    }

    public void setMobileVerified(boolean mobileVerified) {
        this.mobileVerified = mobileVerified;
    }


    public Set<RoleWSO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleWSO> roles) {
        this.roles = roles;
    }
}
