package com.softuni._hibernatecodefirst.services;

import com.softuni._hibernatecodefirst.dao.MedicamentDao;
import com.softuni._hibernatecodefirst.entities.Medicament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicamentServiceImpl implements MedicamentService{

    @Autowired
    private MedicamentDao medicamentDao;

    @Override
    public void create(Medicament medicament) {
        this.medicamentDao.saveAndFlush(medicament);
    }
}
