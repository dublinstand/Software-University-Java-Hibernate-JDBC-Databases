package com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.dao;


import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.Book;
import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.Category;
import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.enums.AgeRestriction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface BookDao extends CrudRepository<Book, Long> {
    Book findById(Long id);

    Iterable<Book> findAll();

    //Write a program that print titles of all books where their age restriction matches the given input (minor, teen or adult). Ignore casing of the input.
    List<Book> findByAgeRestriction(AgeRestriction ageRestriction);

    //Write a program that print titles of the golden edition books and have less than 5000 copies.
    @Query(value = "SELECT b FROM Book AS b WHERE b.editionType = 'GOLD' AND b.copies < 5000")
    List<Book> findGoldenEditionBooksWithLessThan5000Copies();

    //Write a program that print titles and price of books with price lower than 5 and higher than 40.
    @Query(value = "SELECT b FROM Book AS b WHERE b.price < 5 OR b.price > 40")
    List<Book> findBooksWithPriceLowerThan5PriceHigherThan40();

    //Write a program that print titles of all books that are NOT released on given year.
    List<Book> findByReleaseDateNot(Date date);

    //Write a program that print titles of books by given list of categories.
    //The list of categories will be given in a single one separated with one or more spaces.
    List<Book> findByCategoriesIn(List<Category> categories);

    //Write a program that print number of books whose title is longer than a number given as an input.
    @Query(value = "SELECT COUNT(b) FROM Book AS b WHERE LENGTH(b.title) > :len")
    int countByTitleWithLengthMoreThan(@Param(value = "len") int len);

    //Get the most recent books by categories. The categories should be ordered by total book count.
    // Only take the top 3 most recent books from each category - ordered by date (descending), then by title (ascending).
    // Print the category name, total book count and for each book - its title and release date. Get only those categories that have
    // total book count more than 10.
    //Note: Books may appear in several categories.
    @Query(value = "SELECT c, COUNT(b) AS bookCount " +
            "FROM Book AS b " +
            "INNER JOIN b.categories AS c " +
            "GROUP BY c " +
            "HAVING COUNT(b) > 10")
    List<Object[]> findCountOfBooksByCategory();

    List<Book> findTop3ByCategoriesOrderByReleaseDateDescTitleAsc(Category category);

    //Increase Book Copies
    //Write a program that increases the copies of all books released after given date with given number. Print the total amount of book copies that were added.
    //Input
	//On the first line – date in format dd-MMM-yyyy. If a book is released after that date (exclusive) increase her book copies with the provided number from the second line of input
	//On the second line – number of book copies each book should be increased
	//Total number of books that was added to the database

    @Modifying
    @Transactional
    @Query(value = "UPDATE Book AS b " +
            "SET b.copies = b.copies + :copies " +
            "WHERE b.releaseDate > :releaseDate")
    Long increaseBookCopies(@Param(value = "releaseDate") Date releaseDate, @Param(value = "copies") Long copies);

}
