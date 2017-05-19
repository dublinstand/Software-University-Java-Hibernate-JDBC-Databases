package com.softuni._hibernatecodefirst;


import com.softuni._hibernatecodefirst.entities.WizardDeposit;
import com.softuni._hibernatecodefirst.services.WizardDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class Terminal implements CommandLineRunner {

    private final WizardDepositService wizardDepositService;

    @Autowired
    public Terminal(WizardDepositService wizardDepositService) {
        this.wizardDepositService = wizardDepositService;
    }

    @Override
    public void run(String... strings) throws Exception {
        WizardDeposit wd = new WizardDeposit();
        wd.setFirstName("Albus");
        wd.setLastName("Dumbledore");
        wd.setAge(150);
        wd.setMagicWandCreator("Antoich Peverell");
        wd.setMagicWandSize(15);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 11, 11);
        Date depositStart = calendar.getTime();
        wd.setDepositStartDate(depositStart);
        calendar.set(2020, 10, 20);
        Date depositEnd = calendar.getTime();
        wd.setDepositExpirationDate(depositEnd);
        wd.setDepositAmount(2000.24);
        wd.setDepositExpired(false);

        wizardDepositService.persist(wd);
    }
}
