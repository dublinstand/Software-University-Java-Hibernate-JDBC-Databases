package com.airconditioners.core.command;

import com.airconditioners.core.annotations.Insert;
import com.airconditioners.domain.entities.reports.Report;
import com.airconditioners.repositories.ReportRepository;

public class FindReportCommand extends Command {

    @Insert
    private ReportRepository reportRepository;

    protected FindReportCommand(String[] tokens) {
        super(tokens);
    }

    @Override
    public String execute() {
        String manufacturer = super.getTokens()[0];
        String model = super.getTokens()[1];
        int conditionerCount = this.reportRepository.countByModelAndManufacturer(model, manufacturer);
        if(conditionerCount <= 0){
            throw new IllegalArgumentException("The specified entry does not exist.");
        }

        Report report = this.reportRepository.findOneByModelAndManufacturer(model,manufacturer);
        return report.toString();
    }
}
