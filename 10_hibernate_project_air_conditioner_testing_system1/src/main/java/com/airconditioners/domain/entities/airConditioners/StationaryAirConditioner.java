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
        this.setRealEfficiencyRating(this.calculateEffieciencyRating(powerUsage));
    }

    public int getPowerUsage() {
        return this.powerUsage;
    }

    public void setPowerUsage(int powerUsage) {
        if(powerUsage <= 0){
            throw new IllegalArgumentException("Power usage used must be a positive integer.");
        }

        this.powerUsage = powerUsage;
    }

    public EfficiencyRating getRequiredEfficiencyRating() {
        return this.requiredEfficiencyRating;
    }

    public void setRequiredEfficiencyRating(EfficiencyRating requiredEfficiencyRating) {
        this.requiredEfficiencyRating = requiredEfficiencyRating;
    }

    public EfficiencyRating getRealEfficiencyRating() {
        return this.realEfficiencyRating;
    }

    public void setRealEfficiencyRating(EfficiencyRating realEfficiencyRating) {
        this.realEfficiencyRating = realEfficiencyRating;
    }

    private EfficiencyRating calculateEffieciencyRating(int powerUsage){
        EfficiencyRating efficiencyRating;
        if(powerUsage < 1000){
            efficiencyRating = EfficiencyRating.A;
        } else if(powerUsage <=1250){
            efficiencyRating = EfficiencyRating.B;
        } else if(powerUsage <=1500){
            efficiencyRating = EfficiencyRating.C;
        }  else if(powerUsage <=2000){
            efficiencyRating = EfficiencyRating.D;
        } else {
            efficiencyRating = EfficiencyRating.E;
        }

        return efficiencyRating;
    }

    @Override
    public boolean isEfficient() {
        boolean isEffiecient = false;
        if(this.getRealEfficiencyRating().ordinal() <= this.getRequiredEfficiencyRating().ordinal()){
            isEffiecient = true;
        }

        return isEffiecient;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString()).append(System.lineSeparator());
        stringBuilder.append("Required Efficiency Rating : " + this.getRequiredEfficiencyRating());
        stringBuilder.append("Power Usage(KW / h): " + this.getPowerUsage());
        return stringBuilder.toString();
    }
}
