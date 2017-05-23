package com.hibernate._hibernate_relations_01.service;

import com.hibernate._hibernate_relations_01.domain.Book;

public interface BookService {

    void create(Book book);

    void delete(Book book);

    void delete(Long id);

    Book findAuthor(Long id);

    Iterable<Book> findAuthors();
}
