package com.softuni._hibernatecodefirst.services;


import com.softuni._hibernatecodefirst.dao.VisitationDao;
import com.softuni._hibernatecodefirst.entities.Visitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitationServiceImpl implements VisitationService {

    @Autowired
    private VisitationDao visitationDao;

    @Override
    public void create(Visitation visitation) {
        this.visitationDao.saveAndFlush(visitation);
    }
}
