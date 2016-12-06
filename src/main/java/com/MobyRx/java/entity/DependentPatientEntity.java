package com.MobyRx.java.entity;

import com.MobyRx.java.entity.common.BaseEntity;
import com.MobyRx.java.entity.type.RelationshipType;


/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/8/16
 * Time: 10:45 AM
 * To change this template use File | Settings | File Templates.
 */
/*@Entity
@javax.persistence.Table(name = "dependent_patient")*/
public class DependentPatientEntity extends BaseEntity {

    private String name;
    private int age;
    private RelationshipType relationship;
    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public RelationshipType getRelationship() {
        return relationship;
    }

    public void setRelationship(RelationshipType relationship) {
        this.relationship = relationship;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
