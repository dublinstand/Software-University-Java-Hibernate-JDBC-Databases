package com.softuni._hibernatecodefirst.services;

import com.softuni._hibernatecodefirst.entities.WizardDeposit;
import com.softuni._hibernatecodefirst.dao.WizardDepositDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class WizardDepositServiceImpl implements WizardDepositService {

    @Autowired
    private final WizardDepositDao wizardDepositDao;

    public WizardDepositServiceImpl(WizardDepositDao wizardDepositDao) {
        this.wizardDepositDao = wizardDepositDao;
    }

    @Override
    public void persist(WizardDeposit wizardDeposit) {
        this.wizardDepositDao.saveAndFlush(wizardDeposit);
    }

    @Override
    public List<WizardDeposit> findAll() {
        return Collections.unmodifiableList(this.wizardDepositDao.findAll());
    }
}
