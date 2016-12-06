package com.MobyRx.java.service.wso;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/6/16
 * Time: 5:30 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "role")
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
