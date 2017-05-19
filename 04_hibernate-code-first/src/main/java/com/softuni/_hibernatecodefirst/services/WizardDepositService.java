package com.softuni._hibernatecodefirst.services;


import com.softuni._hibernatecodefirst.entities.WizardDeposit;

import java.util.List;

public interface WizardDepositService {

    void persist(WizardDeposit wizardDeposit);

    List<WizardDeposit> findAll();
}
