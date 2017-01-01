package com.MobyRx.java.service.wso;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 1/1/17
 * Time: 8:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class EntityReference {


    private Long id;
    private String name;

    public EntityReference() {
    }

    public EntityReference(Long id) {
        this.id = id;
    }

    public EntityReference(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
