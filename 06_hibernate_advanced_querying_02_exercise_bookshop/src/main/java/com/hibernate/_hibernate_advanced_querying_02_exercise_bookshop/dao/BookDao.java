package com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.dao;


import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends CrudRepository<Book, Long> {
    Book findById(Long id);

    Iterable<Book> findAll();
}
