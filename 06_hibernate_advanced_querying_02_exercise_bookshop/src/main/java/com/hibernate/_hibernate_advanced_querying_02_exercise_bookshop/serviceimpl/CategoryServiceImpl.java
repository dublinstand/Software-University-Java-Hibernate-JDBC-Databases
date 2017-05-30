package com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.serviceimpl;


import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.dao.CategoryDao;
import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.Category;
import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryDao categoryDao;


    @Override
    public void create(Category category) {
        this.categoryDao.save(category);
    }


    @Override
    public void delete(Category category) {

    }

    @Override
    public void delete(Long id) {
        this.categoryDao.delete(id);
    }

    @Override
    public Category findAuthor(Long id) {
        return this.categoryDao.findOne(id);
    }

    @Override
    public Iterable<Category> findAuthors() {
        return this.categoryDao.findAll();
    }

    @Override
    public List<Category> findByNameIn(String[] names) {
        return this.categoryDao.findByNameIn(names);
    }


}
