package com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.service;

import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.Book;
import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.Category;
import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.enums.AgeRestriction;

import java.util.Date;
import java.util.List;

public interface BookService {

    void create(Book book);

    void delete(Book book);

    void delete(Long id);

    Book findAuthor(Long id);

    Iterable<Book> findAuthors();

    List<Book> findByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findGoldenEditionBooksWithLessThan5000Copies();

    List<Book> findByCategoriesIn(List<Category> categories);

    List<Book> findBooksWithPriceLowerThan5PriceHigherThan40();

    List<Book> findByReleaseDateNot(Date date);

    int countByTitleWithLengthMoreThan(int len);

    List<Object[]> findCountOfBooksByCategory();

    List<Book> findByCategoryOrderByReleaseDateDescTitleAsc(Category category);

    Long increaseBookCopies(Date releaseDate, Long copies);


}
