package com.hibernate._hibernate_relations_01.dao;


import com.hibernate._hibernate_relations_01.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends CrudRepository<Book, Long> {
    Book findById(Long id);

    Iterable<Book> findAll();
}
