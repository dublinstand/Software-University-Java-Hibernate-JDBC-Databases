package com.airconditioners.core.command;

import com.airconditioners.core.annotations.Insert;
import com.airconditioners.domain.entities.reports.Report;
import com.airconditioners.repositories.ReportRepository;

import java.util.List;

public class FindAllReportsByManufacturerCommand extends Command {

    @Insert
    private ReportRepository reportRepository;

    protected FindAllReportsByManufacturerCommand(String[] tokens) {
        super(tokens);
    }

    @Override
    public String execute() {
        String manufacturer = super.getTokens()[0];
        List<Report> reports = this.reportRepository.findAllByManufacturer(manufacturer);
        if(reports.size() <= 0){
            throw new IllegalArgumentException("No reports.");
        }

        StringBuilder repotsInfo = new StringBuilder();
        reports.stream().forEach(r -> repotsInfo.append(r.toString()).append(System.lineSeparator()));
        return repotsInfo.toString();
    }
}
