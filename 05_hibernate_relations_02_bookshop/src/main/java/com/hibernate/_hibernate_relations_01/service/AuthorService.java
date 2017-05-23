package com.hibernate._hibernate_relations_01.service;

import com.hibernate._hibernate_relations_01.domain.Author;

public interface AuthorService {

    void create (Author author);

    void delete(Author author);

    void delete(Long id);

    Author findAuthor(Long id);

    Iterable<Author> findAuthors();
}
