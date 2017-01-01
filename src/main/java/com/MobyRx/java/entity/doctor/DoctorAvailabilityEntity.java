package com.MobyRx.java.entity.doctor;

import com.MobyRx.java.entity.common.AccountEntity;
import com.MobyRx.java.entity.common.BaseEntity;
import com.MobyRx.java.entity.common.ProfileEntity;
import com.MobyRx.java.entity.converter.StringArrayToStringConverter;
import com.MobyRx.java.entity.type.WeekDays;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 12/31/16
 * Time: 11:25 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "doctor_availability")
public class DoctorAvailabilityEntity extends BaseEntity{


    private ProfileEntity doctor;
    private WeekDays weekDays;
    private AccountEntity clinic;
    private List<String> timings;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    public ProfileEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(ProfileEntity doctor) {
        this.doctor = doctor;
    }

    @Enumerated(EnumType.STRING)
    public WeekDays getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(WeekDays weekDays) {
        this.weekDays = weekDays;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", nullable = false)
    public AccountEntity getClinic() {
        return clinic;
    }

    public void setClinic(AccountEntity clinic) {
        this.clinic = clinic;
    }

    @Column(name = "timings")
    @Convert(converter = StringArrayToStringConverter.class)
    public List<String> getTimings() {
        return timings;
    }

    public void setTimings(List<String> timings) {
        this.timings = timings;
    }
}
