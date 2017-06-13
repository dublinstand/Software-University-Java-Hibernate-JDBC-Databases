package com.jsonexercise.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jsonexercise.domain.entities.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(value = "SELECT c.name, COUNT(c), AVG(p.price), SUM(p.price) " +
            "FROM Category AS c " +
            "INNER JOIN c.products AS p " +
            "GROUP BY c.name")
    List<Object[]> getCategoryStats();
}