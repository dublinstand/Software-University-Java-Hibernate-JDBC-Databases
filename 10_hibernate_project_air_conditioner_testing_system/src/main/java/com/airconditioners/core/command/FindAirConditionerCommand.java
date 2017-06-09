package com.airconditioners.core.command;

import com.airconditioners.core.annotations.Insert;
import com.airconditioners.domain.entities.airConditioners.BasicAirConditioner;
import com.airconditioners.repositories.BasicAirConditionerRepository;

public class FindAirConditionerCommand extends Command {

    @Insert
    private BasicAirConditionerRepository basicAirConditionerRepository;

    protected FindAirConditionerCommand(String[] tokens) {
        super(tokens);
    }

    @Override
    public String execute() {

        String manufacturer = super.getTokens()[0];
        String model = super.getTokens()[1];

        //throw an error if there is no air conditioner for this model and manufacturer
        int conditionerCount = this.basicAirConditionerRepository.countByModelAndManufacturer(model, manufacturer);
        if (conditionerCount <= 0){
            throw new IllegalArgumentException("The specified entry does not exist");
        }

        BasicAirConditioner basicAirConditioner = this.basicAirConditionerRepository.findOneByModelAndManufacturer(model, manufacturer);
        return basicAirConditioner.toString();
    }
}
