package com.airconditioners.core.command;

import com.airconditioners.core.annotations.Insert;
import com.airconditioners.domain.entities.airConditioners.BasicAirConditioner;
import com.airconditioners.domain.entities.airConditioners.StationaryAirConditioner;
import com.airconditioners.domain.enums.EfficiencyRating;
import com.airconditioners.repositories.BasicAirConditionerRepository;

public class RegisterStationaryAirConditionerCommand extends Command {

    //because of too much code to be done we'll be using the Repositories - no Services, no Factories
    //we need to have @Insert annotation because we will inject it
    @Insert
    private BasicAirConditionerRepository basicAirConditionerRepository;


    public RegisterStationaryAirConditionerCommand(String[] tokens) {
        super(tokens);
    }

    @Override
    public String execute() {
        //this is our command <manufacturer>,<model>,<energyEfficiencyRating>,<powerUsage>
        //we get the tokens using the super.getTokens[n] - from Command.class that we extend
        String manufacturer = super.getTokens()[0];
        String model = super.getTokens()[1];
        //we get the valueOf, using the Enumerator, so that the value will be linked to the Enumerator
        EfficiencyRating requiredEfficiencyRating = EfficiencyRating.valueOf(super.getTokens()[2]);

        int powerUsage = Integer.parseInt(super.getTokens()[3]);

        //we want to verify if an air conditioner with model and manufacturer exists, get the countByModelAndManufacturer of all Air conditioners searched by model and manufacturer
        //if the countByModelAndManufacturer is greater than 0, then it already exists and we throw exception
        int existingAirConditions = this.basicAirConditionerRepository.countByModelAndManufacturer(model, manufacturer);
        if(existingAirConditions > 0){
            throw new IllegalArgumentException("An entry for the given model already exists.");
        }


        //initialize our basicAirConditioner to be a StationaryAirConditioner with passing the data in the constructor
        BasicAirConditioner basicAirConditioner = new StationaryAirConditioner(manufacturer, model, powerUsage, requiredEfficiencyRating);
        //save it
        this.basicAirConditionerRepository.save(basicAirConditioner);

        //this is the success message as per spec: Air Conditioner model [model] from [manufacturer] registered successfully.
        return String.format("Air Conditioner model %s from %s registered successfully.",model,manufacturer);
    }
}
