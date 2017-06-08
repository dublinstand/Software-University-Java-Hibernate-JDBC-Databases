package com.airconditioners.domain.entities.airConditioners;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle_air_conditioner")
public abstract class VehicleAirConditioner extends BasicAirConditioner {

    @Column(name = "volume_covered")
    private int volumeCovered;

    public VehicleAirConditioner() {
    }

    public VehicleAirConditioner(String manufacturer, String model, int volumeCovered) {
        super(manufacturer, model);
        this.setVolumeCovered(volumeCovered);
    }

    public int getVolumeCovered() {
        return volumeCovered;
    }

    public void setVolumeCovered(int volumeCovered) {
        if(volumeCovered < 0){
            throw new IllegalArgumentException("Volume Covered must be a positive integer.");
        }
        this.volumeCovered = volumeCovered;
    }
}
