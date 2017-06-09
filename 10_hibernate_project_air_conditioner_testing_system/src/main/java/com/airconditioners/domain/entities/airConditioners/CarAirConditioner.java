package com.airconditioners.domain.entities.airConditioners;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "car_air_conditioner")
public class CarAirConditioner extends VehicleAirConditioner {

    public CarAirConditioner() {
    }

    public CarAirConditioner(String manufacturer, String model, int volumeCovered) {
        super(manufacturer, model, volumeCovered);
    }

    @Override
    public boolean isEfficient() {
        boolean isEffiecient = true;
        if(Math.sqrt(super.getVolumeCovered()) < 3){
            isEffiecient = false;
        }

        return isEffiecient;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString()).append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
