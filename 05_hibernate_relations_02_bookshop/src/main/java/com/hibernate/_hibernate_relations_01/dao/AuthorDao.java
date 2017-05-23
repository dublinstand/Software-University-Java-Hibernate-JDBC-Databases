package com.hibernate._hibernate_relations_01.dao;


import com.hibernate._hibernate_relations_01.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDao extends CrudRepository<Author, Long> {
    Author findById(Long id);

    Iterable<Author> findAll();
}
