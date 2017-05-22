package com.softuni._hibernatecodefirst.services;

import com.softuni._hibernatecodefirst.dao.DiagnoseDao;
import com.softuni._hibernatecodefirst.entities.Diagnose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagnoseServiceImpl implements DiagnoseService{

    @Autowired
    private DiagnoseDao diagnoseDao;


    @Override
    public void create(Diagnose diagnose) {
        this.diagnoseDao.saveAndFlush(diagnose);
    }
}
