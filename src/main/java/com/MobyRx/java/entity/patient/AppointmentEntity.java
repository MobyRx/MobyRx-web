package com.MobyRx.java.entity.patient;

import com.MobyRx.java.entity.common.AccountEntity;
import com.MobyRx.java.entity.common.BaseEntity;
import com.MobyRx.java.entity.common.ProfileEntity;
import com.MobyRx.java.entity.type.AppointmentStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 12/31/16
 * Time: 11:25 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "patient_appointment")
public class AppointmentEntity extends BaseEntity{

    private ProfileEntity doctor;
    private ProfileEntity patient;
    private AccountEntity clinic;
    private Date appointmentOn;
    private String time;
    private AppointmentStatus status = AppointmentStatus.BOOKED;
    private Integer token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    public ProfileEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(ProfileEntity doctor) {
        this.doctor = doctor;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    public ProfileEntity getPatient() {
        return patient;
    }

    public void setPatient(ProfileEntity patient) {
        this.patient = patient;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", nullable = false)
    public AccountEntity getClinic() {
        return clinic;
    }

    public void setClinic(AccountEntity clinic) {
        this.clinic = clinic;
    }


    @Column(name = "appointment_on", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getAppointmentOn() {
        return appointmentOn;
    }

    public void setAppointmentOn(Date appointmentOn) {
        this.appointmentOn = appointmentOn;
    }

    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Enumerated(EnumType.STRING)
    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }


    @Column(name = "token")
    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }
}
