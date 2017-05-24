package com.hibernate._hibernate_relations_01.serviceimpl;


import com.hibernate._hibernate_relations_01.dao.CategoryDao;
import com.hibernate._hibernate_relations_01.domain.Category;
import com.hibernate._hibernate_relations_01.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
