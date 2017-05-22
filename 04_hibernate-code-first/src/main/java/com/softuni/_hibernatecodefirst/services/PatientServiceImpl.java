package com.softuni._hibernatecodefirst.services;


import com.softuni._hibernatecodefirst.dao.PatientDao;
import com.softuni._hibernatecodefirst.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientDao patientDao;

    @Override
    public void create(Patient patient) {
        this.patientDao.saveAndFlush(patient);
    }

    @Override
    public Patient retrieve(long id) {
        return this.patientDao.findOne(id);
    }
}
