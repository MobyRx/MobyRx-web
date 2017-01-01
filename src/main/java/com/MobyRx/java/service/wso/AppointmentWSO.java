package com.MobyRx.java.service.wso;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 1/1/17
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "appointment")
public class AppointmentWSO {

    private Long id;
    private EntityReference doctor;
    private EntityReference patient;
    private EntityReference clinic;
    private Date appointmentOn;
    private String time;
    private Integer token;
    private String status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public EntityReference getDoctor() {
        return doctor;
    }

    public void setDoctor(EntityReference doctor) {
        this.doctor = doctor;
    }

    public EntityReference getPatient() {
        return patient;
    }

    public void setPatient(EntityReference patient) {
        this.patient = patient;
    }

    public EntityReference getClinic() {
        return clinic;
    }

    public void setClinic(EntityReference clinic) {
        this.clinic = clinic;
    }

    public Date getAppointmentOn() {
        return appointmentOn;
    }

    public void setAppointmentOn(Date appointmentOn) {
        this.appointmentOn = appointmentOn;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
