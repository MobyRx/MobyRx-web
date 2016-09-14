package com.MobyRx.java.service.wso;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/6/16
 * Time: 1:10 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "user")
public class UserWSO {
    
    private String success;
    private String message;
    private String authToken;
    private String isExistingUser;

    @XmlElement(name = "success")
    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @XmlElement(name = "AuthToken")
    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getExistingUser() {
        return isExistingUser;
    }

    public void setExistingUser(String existingUser) {
        isExistingUser = existingUser;
    }
}
