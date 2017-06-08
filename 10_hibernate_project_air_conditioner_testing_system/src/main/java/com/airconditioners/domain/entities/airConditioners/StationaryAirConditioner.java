package com.airconditioners.domain.entities.airConditioners;

import com.airconditioners.domain.enums.EfficiencyRating;

import javax.persistence.*;

@Entity
@Table(name = "stationary_air_conditioner")
public class StationaryAirConditioner extends BasicAirConditioner {

    @Column(name = "power_usage")
    private int powerUsage;

    @Enumerated(EnumType.STRING)
    private EfficiencyRating requiredEfficiencyRating;

    @Enumerated(EnumType.STRING)
    private EfficiencyRating realEfficiencyRating;

    public StationaryAirConditioner() {
    }

    public StationaryAirConditioner(String manufacturer, String model, int powerUsage, EfficiencyRating requiredEfficiencyRating) {
        super(manufacturer, model);
        this.setPowerUsage(powerUsage);
        this.setRequiredEfficiencyRating(requiredEfficiencyRating);
        this.setRealEfficiencyRating(this.calculateEfficiencyRating(powerUsage));
    }

    public int getPowerUsage() {
        return powerUsage;
    }

    public void setPowerUsage(int powerUsage) {
        if(powerUsage <= 0){
            throw new IllegalArgumentException("Power Usage must be a positive integer.");
        }
        this.powerUsage = powerUsage;
    }

    public EfficiencyRating getRequiredEfficiencyRating() {
        return requiredEfficiencyRating;
    }

    public void setRequiredEfficiencyRating(EfficiencyRating requiredEfficiencyRating) {
        this.requiredEfficiencyRating = requiredEfficiencyRating;
    }

    public EfficiencyRating getRealEfficiencyRating() {
        return realEfficiencyRating;
    }

    public void setRealEfficiencyRating(EfficiencyRating realEfficiencyRating) {
        this.realEfficiencyRating = realEfficiencyRating;
    }

    private EfficiencyRating calculateEfficiencyRating(int powerUsage) {
        EfficiencyRating efficiencyRating;
        if (powerUsage < 1000) {
            efficiencyRating = EfficiencyRating.A;
        } else if (powerUsage <= 1250) {
            efficiencyRating = EfficiencyRating.B;
        } else if (powerUsage <= 1500) {
            efficiencyRating = EfficiencyRating.C;
        } else if (powerUsage <= 2000) {
            efficiencyRating = EfficiencyRating.D;
        } else {
            efficiencyRating = EfficiencyRating.E;
        }

        return efficiencyRating;
    }

    @Override
    protected boolean isEfficient() {
        boolean isEfficient = false;

        //with ordinal we get the order
        if(this.getRealEfficiencyRating().ordinal() >= this.getRealEfficiencyRating().ordinal()){
            isEfficient = true;
        }

        return false;
    }
}
