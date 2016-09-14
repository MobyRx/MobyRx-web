package com.MobyRx.java.service.wso;

import com.sun.org.apache.bcel.internal.generic.LSTORE;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/13/16
 * Time: 12:38 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "status")
public class StatusWSO {
    
    private int code;
    private String message;
    private List<String> errors;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
    
    public void addError(String error){
        if(null== getErrors())
            setErrors(new ArrayList<String>());
        getErrors().add(error);
    }
}
