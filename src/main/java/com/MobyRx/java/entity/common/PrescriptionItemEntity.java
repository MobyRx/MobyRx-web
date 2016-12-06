package com.MobyRx.java.entity.common;

import com.MobyRx.java.entity.doctor.DrugsEntity;
import com.MobyRx.java.entity.type.DoseType;
import com.MobyRx.java.entity.type.DurationType;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/6/16
 * Time: 9:48 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "prescription_item")
@XmlRootElement(name = "prescription_item")
public class PrescriptionItemEntity extends BaseEntity {

    private PrescriptionEntity prescription;
    private DrugsEntity drugs;
    private String drugName;
    private String instruction;
    private int quantity;
    private int duration;
    private DurationType durationType;
    private boolean beforeFood = false;
    private DoseType doseType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prescription_id", nullable = false)
    public PrescriptionEntity getPrescription() {
        return prescription;
    }

    public void setPrescription(PrescriptionEntity prescription) {
        this.prescription = prescription;
    }

    @ManyToOne()
    @JoinColumn(name = "drug_id", nullable = false)
    public DrugsEntity getDrugs() {
        return drugs;
    }

    public void setDrugs(DrugsEntity drugs) {
        this.drugs = drugs;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Enumerated(EnumType.STRING)
    public DurationType getDurationType() {
        return durationType;
    }

    public void setDurationType(DurationType durationType) {
        this.durationType = durationType;
    }

    @Column(name = "is_before_food")
    public boolean isBeforeFood() {
        return beforeFood;
    }

    public void setBeforeFood(boolean beforeFood) {
        this.beforeFood = beforeFood;
    }

    @Enumerated(EnumType.STRING)
    public DoseType getDoseType() {
        return doseType;
    }

    public void setDoseType(DoseType doseType) {
        this.doseType = doseType;
    }
}
