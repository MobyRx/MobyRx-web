package com.MobyRx.java.entity;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/8/16
 * Time: 10:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class EmergencyContact {
    
    private String name;
    private String mobile;

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


    @Override
    public String toString() {
        return this.name+"-"+this.mobile;
    }
}
