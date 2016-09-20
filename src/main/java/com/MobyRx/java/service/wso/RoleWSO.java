package com.MobyRx.java.service.wso;

import com.MobyRx.java.entity.BaseEntity;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/6/16
 * Time: 5:30 PM
 * To change this template use File | Settings | File Templates.
 */

public class RoleWSO extends BaseWSO{

   
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
