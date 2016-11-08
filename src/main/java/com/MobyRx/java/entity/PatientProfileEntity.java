package com.MobyRx.java.entity;

import com.MobyRx.java.entity.type.BloodGroup;
import com.MobyRx.java.entity.type.Gender;
import com.MobyRx.java.entity.type.RelationshipType;

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
@Entity
public class PatientProfileEntity extends ProfileEntity{

    private Date dob;
    private int age;
    private BloodGroup bloodGroup;
    private PatientProfileEntity parentPatient;
    private RelationshipType relationship;


    @Column(name = "date_of_birth")
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

    @Column(name = "blood_group")
    @Enumerated
    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parents_id", nullable = true)
    public PatientProfileEntity getParentPatient() {
        return parentPatient;
    }

    public void setParentPatient(PatientProfileEntity parentPatient) {
        this.parentPatient = parentPatient;
    }

    @Enumerated
    @Column(name = "relationship")
    public RelationshipType getRelationship() {
        return relationship;
    }

    public void setRelationship(RelationshipType relationship) {
        this.relationship = relationship;
    }
}
