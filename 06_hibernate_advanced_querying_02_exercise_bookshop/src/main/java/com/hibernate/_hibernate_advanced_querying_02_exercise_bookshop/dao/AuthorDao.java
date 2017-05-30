package com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.dao;


import com.hibernate._hibernate_advanced_querying_02_exercise_bookshop.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorDao extends CrudRepository<Author, Long> {
    Author findById(Long id);

    Iterable<Author> findAll();

    List<Author> findByFirstNameStartsWith(String letters);

    //Write a program that print the total number of book copies by author. Order the results descending by total book copies.
    //It will return two objects - first will be Author object, the second - the count of the author(which has the books)
    @Query(value = "SELECT a, SUM(b.copies) AS copies " +
            "FROM Book AS b " +
            "INNER JOIN b.author AS a " +
            "GROUP BY a " +
            "ORDER BY copies DESC")
    List<Object[]> findSumOfCopies();
}
