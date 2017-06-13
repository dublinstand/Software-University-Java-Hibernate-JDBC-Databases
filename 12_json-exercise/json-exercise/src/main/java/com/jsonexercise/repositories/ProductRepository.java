package com.jsonexercise.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jsonexercise.domain.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "SELECT p FROM Product AS p " +
            "LEFT JOIN p.buyer AS b " +
            "WHERE p.price BETWEEN 500 AND 1000 " +
            "AND p.buyer IS NULL")
    List<Product> findWithoutBuyer();
}