package com.airconditioners.core.command;

import com.airconditioners.core.annotations.Insert;
import com.airconditioners.domain.entities.airConditioners.BasicAirConditioner;
import com.airconditioners.domain.entities.reports.Report;
import com.airconditioners.domain.enums.Mark;
import com.airconditioners.repositories.BasicAirConditionerRepository;
import com.airconditioners.repositories.ReportRepository;

public class TestAirConditionerCommand extends Command {

    @Insert
    private ReportRepository reportRepository;

    @Insert
    private BasicAirConditionerRepository basicAirConditionerRepository;

    public TestAirConditionerCommand(String[] tokens) {
        super(tokens);
    }

    @Override
    public String execute() {
        String manufacturer = super.getTokens()[0];
        String model = super.getTokens()[1];

        //we need to verify if the report for this model and manufacturer exist
        int reportCount = this.reportRepository.countByModelAndManufacturer(model, manufacturer);

        //the exception will be thrown in the Engine method and we try/catch it there
        if (reportCount > 0){
            throw new IllegalArgumentException("An entry for this given model already exists");
        }

        //throw an error if there is no air conditioner for this model and manufacturer
        int conditionerCount = this.basicAirConditionerRepository.countByModelAndManufacturer(model, manufacturer);
        if (conditionerCount <= 0){
            throw new IllegalArgumentException("The specified entry does not exist");
        }

        //we get the basicAirConditioner by model and name in order to pass it to the report
        BasicAirConditioner basicAirConditioner = this.basicAirConditionerRepository.findOneByModelAndManufacturer(model, manufacturer);

        //we need to verify if the air conditioner is effective, it is done by the abstract method isEfficient in BasicAirConditioner
        //which is different for all air conditioner types
        boolean isEfficient = basicAirConditioner.isEfficient();
        //this is our mark
        Mark mark;
        if (isEfficient){
            mark = Mark.PASSED;
        } else {
            mark = Mark.FAILED;
        }

        Report report = new Report(mark, basicAirConditioner);
        this.reportRepository.save(report);

        return String.format("Air Conditioner model %s from %s Tested successfully.",model,manufacturer);
    }
}
