package com.MobyRx.java.service.wso;



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

public class PatientProfileWSO extends ProfileWSO{

    private Date dob;
    private int age;
    private BloodGroupWSO bloodGroup;
    private PatientProfileWSO parentPatient;
    private RelationshipWSO relationship;


  
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BloodGroupWSO getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroupWSO bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public PatientProfileWSO getParentPatient() {
        return parentPatient;
    }

    public void setParentPatient(PatientProfileWSO parentPatient) {
        this.parentPatient = parentPatient;
    }


    public RelationshipWSO getRelationship() {
        return relationship;
    }

    public void setRelationship(RelationshipWSO relationship) {
        this.relationship = relationship;
    }
}
