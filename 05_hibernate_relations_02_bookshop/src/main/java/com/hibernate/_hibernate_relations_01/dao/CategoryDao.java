package com.hibernate._hibernate_relations_01.dao;


import com.hibernate._hibernate_relations_01.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends CrudRepository<Category, Long> {
    Category findById(Long id);

    Iterable<Category> findAll();
}
