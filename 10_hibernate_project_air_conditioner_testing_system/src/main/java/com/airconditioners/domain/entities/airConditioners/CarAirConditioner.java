package com.airconditioners.domain.entities.airConditioners;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle_air_conditioner")
public class CarAirConditioner extends VehicleAirConditioner {

    public CarAirConditioner() {
    }

    public CarAirConditioner(String manufacturer, String model, int volumeCovered) {
        super(manufacturer, model, volumeCovered);
    }

    @Override
    protected boolean isEfficient() {
        boolean isEfficient = true;

        if (Math.sqrt(super.getVolumeCovered()) < 3){
            isEfficient = false;
        }

        return isEfficient;
    }
}
