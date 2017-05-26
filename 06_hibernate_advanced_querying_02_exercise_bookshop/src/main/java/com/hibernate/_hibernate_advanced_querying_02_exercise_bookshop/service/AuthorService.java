package com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.service;

import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.Author;

public interface AuthorService {

    void create (Author author);

    void delete(Author author);

    void delete(Long id);

    Author findAuthor(Long id);

    Iterable<Author> findAuthors();
}
