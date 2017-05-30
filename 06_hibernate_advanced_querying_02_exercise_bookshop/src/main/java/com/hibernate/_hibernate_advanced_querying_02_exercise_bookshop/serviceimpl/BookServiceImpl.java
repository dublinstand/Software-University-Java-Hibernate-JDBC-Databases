package com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.serviceimpl;


import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.dao.BookDao;
import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.Book;
import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.Category;
import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.enums.AgeRestriction;
import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Override
    public List<Book> findByAgeRestriction(AgeRestriction ageRestriction) {
        return this.bookDao.findByAgeRestriction(ageRestriction);
    }

    @Override
    public List<Book> findGoldenEditionBooksWithLessThan5000Copies() {
        return this.bookDao.findGoldenEditionBooksWithLessThan5000Copies();
    }

    @Override
    public List<Book> findByCategoriesIn(List<Category> categories) {
        return this.bookDao.findByCategoriesIn(categories);
    }

    @Override
    public List<Book> findBooksWithPriceLowerThan5PriceHigherThan40() {
        return this.bookDao.findBooksWithPriceLowerThan5PriceHigherThan40();
    }

    @Override
    public List<Book> findByReleaseDateNot(Date date) {
        return this.bookDao.findByReleaseDateNot(date);
    }

    @Override
    public int countByTitleWithLengthMoreThan(int len) {
        return this.bookDao.countByTitleWithLengthMoreThan(len);
    }

    @Override
    public List<Object[]> findCountOfBooksByCategory() {
        return this.bookDao.findCountOfBooksByCategory();
    }

    @Override
    public List<Book> findByCategoryOrderByReleaseDateDescTitleAsc(Category category) {
        return this.bookDao.findTop3ByCategoriesOrderByReleaseDateDescTitleAsc(category);
    }

    @Override
    public Long increaseBookCopies(Date releaseDate, Long copies) {
        return this.bookDao.increaseBookCopies(releaseDate, copies);
    }
}
