package com.MobyRx.java.entity.master;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.MobyRx.java.entity.common.BaseEntity;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/6/16
 * Time: 5:30 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "role")
@XmlRootElement(name = "role")
public class RoleEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 3256446889020622647L;
    
    
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
