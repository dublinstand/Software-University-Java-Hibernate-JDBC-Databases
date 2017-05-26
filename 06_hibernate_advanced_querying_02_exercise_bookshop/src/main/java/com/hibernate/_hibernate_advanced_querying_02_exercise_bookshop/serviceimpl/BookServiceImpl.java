package com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.serviceimpl;


import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.dao.BookDao;
import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.Book;
import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.service.BookService;
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
