package com.hibernate._hibernate_relations_01.serviceimpl;


import com.hibernate._hibernate_relations_01.dao.BookDao;
import com.hibernate._hibernate_relations_01.domain.Book;
import com.hibernate._hibernate_relations_01.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDao bookDao;


    @Override
    public void create(Book book) {
        this.bookDao.save(book);
    }

    @Override
    public void delete(Book book) {
        this.bookDao.delete(book);
    }

    @Override
    public void delete(Long id) {
        this.bookDao.delete(id);
    }

    @Override
    public Book findAuthor(Long id) {
        return this.findAuthor(id);
    }

    @Override
    public Iterable<Book> findAuthors() {
        return this.findAuthors();
    }
}
