package com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.service;

import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.Book;
import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.enums.AgeRestriction;

import java.util.List;

public interface BookService {

    void create(Book book);

    void delete(Book book);

    void delete(Long id);

    Book findAuthor(Long id);

    Iterable<Book> findAuthors();

    List<Book> findByAgeRestriction(AgeRestriction ageRestriction);
}
