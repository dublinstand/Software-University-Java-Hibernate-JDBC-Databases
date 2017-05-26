package com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.dao;


import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.Book;
import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.enums.AgeRestriction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends CrudRepository<Book, Long> {
    Book findById(Long id);

    Iterable<Book> findAll();

//    Write a program that print titles of all books where their age restriction matches the given input (minor, teen or adult). Ignore casing of the input.
    List<Book>  findByAgeRestriction(AgeRestriction ageRestriction);
}
