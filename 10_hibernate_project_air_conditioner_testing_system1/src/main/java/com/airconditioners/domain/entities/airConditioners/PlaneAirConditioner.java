package com.airconditioners.domain.entities.airConditioners;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "plane_air_conditioner")
public class PlaneAirConditioner extends VehicleAirConditioner {

    @Column(name = "electricity_used")
    private int electricityUsed;

    public PlaneAirConditioner() {
    }

    public PlaneAirConditioner(String manufacturer, String model, int volumeCovered, int electricityUsed) {
        super(manufacturer, model, volumeCovered);
        this.setElectricityUsed(electricityUsed);
    }

    public int getElectricityUsed() {
        return this.electricityUsed;
    }

    public void setElectricityUsed(int electricityUsed) {
        if(electricityUsed <= 0){
            throw new IllegalArgumentException("Electricity used must be a positive integer.");
        }

        this.electricityUsed = electricityUsed;
    }

    @Override
    public boolean isEfficient() {
        boolean isEfficient = false;
        if(this.getElectricityUsed()/Math.sqrt(super.getVolumeCovered()) < 150){
            isEfficient = true;
        }

        return isEfficient;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString()).append(System.lineSeparator());
        stringBuilder.append("Electricity Used : " + this.getElectricityUsed());
        return stringBuilder.toString();
    }
}
