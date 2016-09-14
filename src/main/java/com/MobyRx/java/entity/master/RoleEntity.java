package com.MobyRx.java.entity.master;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
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
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 3256446889020622647L;
    
    
    private Long id;
    private String name;
    private String description;


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true, nullable = false, updatable = false)
    @XmlTransient
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
