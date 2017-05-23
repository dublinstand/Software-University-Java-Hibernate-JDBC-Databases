package com.hibernate._hibernate_relations_01.service;

import com.hibernate._hibernate_relations_01.domain.Category;

public interface CategoryService {

    void create(Category category);

    void delete(Category category);

    void delete(Long id);

    Category findAuthor(Long id);

    Iterable<Category> findAuthors();
}
