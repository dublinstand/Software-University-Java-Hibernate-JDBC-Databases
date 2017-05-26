package com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.dao;


import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDao extends CrudRepository<Author, Long> {
    Author findById(Long id);

    Iterable<Author> findAll();
}
