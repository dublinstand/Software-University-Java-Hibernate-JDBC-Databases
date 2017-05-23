package com.hibernate._hibernate_relations_01.serviceimpl;

import com.hibernate._hibernate_relations_01.dao.ClassicLabelDao;
import com.hibernate._hibernate_relations_01.entities.labels.ClassicLabel;
import com.hibernate._hibernate_relations_01.service.ClassicLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassicLabelServiceImpl implements ClassicLabelService {

    @Autowired
    private ClassicLabelDao classicLabelDao;


    @Override
    public void create(ClassicLabel classicLabel) {
        this.classicLabelDao.saveAndFlush(classicLabel);
    }
}
