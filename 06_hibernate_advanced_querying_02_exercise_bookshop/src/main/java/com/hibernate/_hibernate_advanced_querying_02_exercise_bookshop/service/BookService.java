package com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.service;

import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.Book;

public interface BookService {

    void create(Book book);

    void delete(Book book);

    void delete(Long id);

    Book findAuthor(Long id);

    Iterable<Book> findAuthors();
}
