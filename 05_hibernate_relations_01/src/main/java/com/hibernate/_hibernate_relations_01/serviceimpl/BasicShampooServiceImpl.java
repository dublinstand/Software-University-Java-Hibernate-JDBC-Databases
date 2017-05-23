package com.hibernate._hibernate_relations_01.serviceimpl;

import com.hibernate._hibernate_relations_01.dao.BasicShampooDao;
import com.hibernate._hibernate_relations_01.entities.shampoos.BasicShampoo;
import com.hibernate._hibernate_relations_01.service.BasicShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicShampooServiceImpl implements BasicShampooService {

    @Autowired
    private BasicShampooDao basicShampooDao;


    @Override
    public void create(BasicShampoo basicShampoo) {
        basicShampooDao.saveAndFlush(basicShampoo);
    }
}
