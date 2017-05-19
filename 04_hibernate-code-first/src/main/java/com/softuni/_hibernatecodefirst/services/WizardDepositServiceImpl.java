package com.softuni._hibernatecodefirst.services;

import com.softuni._hibernatecodefirst.entities.WizardDeposit;
import com.softuni._hibernatecodefirst.repository.WizardDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class WizardDepositServiceImpl implements WizardDepositService {

    @Autowired
    private final WizardDepositRepository wizardDepositRepository;

    public WizardDepositServiceImpl(WizardDepositRepository wizardDepositRepository) {
        this.wizardDepositRepository = wizardDepositRepository;
    }

    @Override
    public void persist(WizardDeposit wizardDeposit) {
        this.wizardDepositRepository.saveAndFlush(wizardDeposit);
    }

    @Override
    public List<WizardDeposit> findAll() {
        return Collections.unmodifiableList(this.wizardDepositRepository.findAll());
    }
}
