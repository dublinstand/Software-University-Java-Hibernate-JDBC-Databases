package com.hibernate._hibernate_relations_01.serviceimpl;

import com.hibernate._hibernate_relations_01.dao.BasicIngredientDao;
import com.hibernate._hibernate_relations_01.entities.ingredients.BasicIngredient;
import com.hibernate._hibernate_relations_01.service.BasicIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicIngredientServiceImpl implements BasicIngredientService {

    @Autowired
    private BasicIngredientDao basicIngredientDao;

    @Override
    public void create(BasicIngredient basicIngredient) {
        this.basicIngredientDao.saveAndFlush(basicIngredient);

    }

    @Override
    public BasicIngredient retrieve(long id) {
        return this.basicIngredientDao.findOne(id);
    }
}
