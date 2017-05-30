package com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.service;

import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.Category;

import java.util.List;

public interface CategoryService {

    void create(Category category);

    void delete(Category category);

    void delete(Long id);

    Category findAuthor(Long id);

    Iterable<Category> findAuthors();

    List<Category> findByNameIn(String[] names);

}
