package com.MobyRx.java.service.wso;



import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.MobyRx.java.service.wso.*;

@XmlRootElement(name = "PrescriptionItemWSO")
public class PrescriptionItemWSO extends BaseWSO{

    private PrescriptionWSO prescription;
    private DrugWSO drugs;
    private String drugName;
    private String instruction;
    private int quantity;
    private int duration;
    private DurationWSO durationType;
    private boolean beforeFood = false;
    private DoseWSO doseType;


   
    public PrescriptionWSO getPrescription() {
        return prescription;
    }

    public void setPrescription(PrescriptionWSO prescription) {
        this.prescription = prescription;
    }

    public DrugWSO getDrugs() {
        return drugs;
    }

    public void setDrugs(DrugWSO drugs) {
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


    public DurationWSO getDurationType() {
        return durationType;
    }

    public void setDurationType(DurationWSO durationType) {
        this.durationType = durationType;
    }

    public boolean isBeforeFood() {
        return beforeFood;
    }

    public void setBeforeFood(boolean beforeFood) {
        this.beforeFood = beforeFood;
    }


    public DoseWSO getDoseType() {
        return doseType;
    }

    public void setDoseType(DoseWSO doseType) {
        this.doseType = doseType;
    }
}
