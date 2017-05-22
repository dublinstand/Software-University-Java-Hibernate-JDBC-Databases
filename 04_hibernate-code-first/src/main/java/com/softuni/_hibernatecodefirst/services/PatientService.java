package com.softuni._hibernatecodefirst.services;


import com.softuni._hibernatecodefirst.entities.Patient;

public interface PatientService {

    void create(Patient patient);
    Patient retrieve(long id);
}
